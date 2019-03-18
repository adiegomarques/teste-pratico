package com.diegomarques.testepratico.api.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    private static final long serialVersionUID = 6524560251526772839L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_pagamento", nullable = false)
    private Date dataPagamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_vencimento", nullable = false)
    private Date dataVencimento;

    @Column(name = "valor_original", nullable = false)
    private BigDecimal valorOriginal;

    @Column(name = "dias_atraso", nullable = false)
    private Long diasAtraso;

    @Column(name = "regra_juros", nullable = false)
    private String regraJuros;

    @Column(name = "valor_corrigido", nullable = false)
    private BigDecimal valorCorrigido;

    public BigDecimal getValorCorrigido() {
        return valorCorrigido;
    }

    public void setValorCorrigido(BigDecimal valorCorrigido) {
        this.valorCorrigido = valorCorrigido;
    }

    public Conta() {
    }

    public String getRegraJuros() {
        return regraJuros;
    }

    public void setRegraJuros(String regraJuros) {
        this.regraJuros = regraJuros;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(Long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .append("dataPagamento", dataPagamento)
                .append("dataVencimento", dataVencimento)
                .append("valorOriginal", valorOriginal)
                .append("diasAtraso", diasAtraso)
                .append("regraJuros", regraJuros)
                .toString();
    }
}
