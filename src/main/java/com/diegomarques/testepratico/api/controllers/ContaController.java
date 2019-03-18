package com.diegomarques.testepratico.api.controllers;

import com.diegomarques.testepratico.api.dtos.ContaRequest;
import com.diegomarques.testepratico.api.dtos.ContaResponse;
import com.diegomarques.testepratico.api.entities.Conta;
import com.diegomarques.testepratico.api.enums.RegraJuros;
import com.diegomarques.testepratico.api.response.Response;
import com.diegomarques.testepratico.api.services.ContaService;
import com.diegomarques.testepratico.api.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/contas")
@CrossOrigin(origins = "*")
public class ContaController {

    private static final Logger log = LoggerFactory.getLogger(ContaController.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ContaService contaService;

    @Value("${paginacao.qtd_por_pagina}")
    private int qtdPorPagina;

    public ContaController() {
    }

    /**
     * Retorna a listagem de contas.
     *
     * @return ResponseEntity<Response < ContaResponse>>
     */
    @GetMapping()
    public ResponseEntity<Response<Page<ContaResponse>>> listar(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {
        log.info("Buscando congtas a pagar, página: {}", pag);
        Response<Page<ContaResponse>> response = new Response<Page<ContaResponse>>();

        PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
        Page<Conta> contas = this.contaService.listar(pageRequest);
        Page<ContaResponse> contaDtos = contas.map(lancamento -> this.converterContaParaResponse(lancamento));

        response.setData(contaDtos);
        return ResponseEntity.ok(response);
    }

    /**
     * Adiciona uma novo conta.
     *
     * @param result
     * @return ResponseEntity<Response < ContaResponse>>
     * @throws ParseException
     */
    @PostMapping
    public ResponseEntity<Response<ContaResponse>> adicionar(@Valid @RequestBody ContaRequest request,
                                                             BindingResult result) throws ParseException {
        log.info("Adicionando Conta: {}", request.toString());
        Response<ContaResponse> response = new Response<ContaResponse>();

        Conta conta = this.converterResquetParaConta(request, result);

        if (result.hasErrors()) {
            log.error("Erro validando conta: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        conta = this.contaService.persistir(conta);
        response.setData(this.converterContaParaResponse(conta));
        return ResponseEntity.ok(response);
    }

    /**
     * Converte uma entidade conta para seu respectivo DTO.
     *
     * @param conta
     * @return ContaResponse
     */
    private ContaResponse converterContaParaResponse(Conta conta) {
        ContaResponse contaResponse = new ContaResponse();
        contaResponse.setDataPagamento(conta.getDataPagamento());
        contaResponse.setDataVencimento(conta.getDataVencimento());
        contaResponse.setNome(conta.getNome());
        contaResponse.setValorOriginal(conta.getValorOriginal());
        contaResponse.setValorCorrigido(conta.getValorCorrigido());
        contaResponse.setDiasAtraso(conta.getDiasAtraso());

        return contaResponse;
    }


    /**
     * Converte uma ContaResponse para uma entidade Conta.
     *
     * @param request
     * @param result
     * @return Conta
     * @throws ParseException
     */
    private Conta converterResquetParaConta(ContaRequest request, BindingResult result) throws ParseException {
        Conta conta = new Conta();
        conta.setDataPagamento(request.getDataPagamento());
        conta.setDataVencimento(request.getDataVencimento());
        conta.setNome(request.getNome());
        conta.setValorOriginal(request.getValorOriginal());

        Long diasDeAtrado = DateUtil.getDiasAtraso(request.getDataVencimento(), request.getDataPagamento());

        if (diasDeAtrado > 0) {
            if (diasDeAtrado <= 3) {
                conta.setValorCorrigido(calculaJuros(RegraJuros.ATE_TRES_DIAS, diasDeAtrado, request.getValorOriginal()));
                conta.setRegraJuros(RegraJuros.ATE_TRES_DIAS.toString());
            } else if (diasDeAtrado >= 5) {
                conta.setValorCorrigido(calculaJuros(RegraJuros.ATE_CINCO_DIAS, diasDeAtrado, request.getValorOriginal()));
                conta.setRegraJuros(RegraJuros.ATE_CINCO_DIAS.toString());
            } else {
                conta.setValorCorrigido(calculaJuros(RegraJuros.SUPERIOR_CINCO_DIAS, diasDeAtrado, request.getValorOriginal()));
                conta.setRegraJuros(RegraJuros.SUPERIOR_CINCO_DIAS.toString());
            }

        } else {
            conta.setValorCorrigido(conta.getValorOriginal());
            conta.setRegraJuros(RegraJuros.PAGO_DENTO_PRAZO.toString());
        }
        conta.setDiasAtraso(diasDeAtrado > 0 ? diasDeAtrado : 0l);
        return conta;
    }


    private BigDecimal calculaJuros(RegraJuros regra, Long diasAtraso, BigDecimal valorOriginal) {
        Double valorMulta = (valorOriginal.doubleValue() * regra.getMulta())/100;
        Double jurosDiarios = diasAtraso * regra.getJuros();
        Double valorJuros = (valorOriginal.doubleValue() * jurosDiarios)/100;
        return valorOriginal.add(new BigDecimal(valorJuros)).add(new BigDecimal(valorMulta)).setScale(2, RoundingMode.HALF_DOWN);

    }
}