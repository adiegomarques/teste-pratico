package com.diegomarques.testepratico.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class ContaResponse {

    private String nome;

    private Date dataPagamento;

    private Date dataVencimento;

    private BigDecimal valorOriginal;

    private BigDecimal valorCorrigido;

    private Long diasAtraso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorCorrigido() {
        return valorCorrigido;
    }

    public void setValorCorrigido(BigDecimal valorCorrigido) {
        this.valorCorrigido = valorCorrigido;
    }

    public Long getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(Long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("nome", nome)
                .append("dataPagamento", dataPagamento)
                .append("dataVencimento", dataVencimento)
                .append("valorOriginal", valorOriginal)
                .append("valorCorrigido", valorCorrigido)
                .append("diasAtraso", diasAtraso)
                .toString();
    }
}
