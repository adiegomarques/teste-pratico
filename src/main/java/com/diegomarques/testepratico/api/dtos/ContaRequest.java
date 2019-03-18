package com.diegomarques.testepratico.api.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class ContaRequest {

    @NotEmpty(message = "Campo nome é obrigatório ")
    private String nome;

    @NotNull(message = "Campo Data Pagamento é obrigatório ")
    private Date dataPagamento;

    @NotNull(message = "Campo Data Vemcimento é obrigatório ")
    private Date dataVencimento;

    @NotNull(message = "Campo Valor Original é obrigatório ")
    private BigDecimal valorOriginal;


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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("nome", nome)
                .append("dataPagamento", dataPagamento)
                .append("dataVencimento", dataVencimento)
                .append("valorOriginal", valorOriginal)
                .toString();
    }
}
