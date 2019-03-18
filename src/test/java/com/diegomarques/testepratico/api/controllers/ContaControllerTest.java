package com.diegomarques.testepratico.api.controllers;

import com.diegomarques.testepratico.api.entities.Conta;
import com.diegomarques.testepratico.api.services.ContaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContaService contaService;

    private static final String URL_BASE = "/api/contas/";

    @Test
    public void testCadastrarConta() throws Exception {
        Conta conta = obterDadosConta();
        BDDMockito.given(this.contaService.persistir(Mockito.any(Conta.class))).willReturn(conta);

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nome").value(conta.getNome()))
                .andExpect(jsonPath("$.data.valorOriginal").value(conta.getValorOriginal()))
                .andExpect(jsonPath("$.data.diasAtraso").value(10l))
                .andExpect(jsonPath("$.data.valorCorrigido").value(new BigDecimal(105)))
                .andExpect(jsonPath("$.errors").isEmpty());
    }


    private Conta obterDadosConta() {
        Conta conta = new Conta();
        conta.setNome("TEste 1");
        conta.setDataVencimento(new Date());
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, -10);
        dt = c.getTime();
        conta.setDataPagamento(dt);
        conta.setValorOriginal(new BigDecimal(100));

        return conta;
    }

}
