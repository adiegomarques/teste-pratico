package com.diegomarques.testepratico.api.enums;

public enum RegraJuros {
    PAGO_DENTO_PRAZO(0, 0d),
    ATE_TRES_DIAS(2, 0.1d),
    ATE_CINCO_DIAS(3, 0.2d),
    SUPERIOR_CINCO_DIAS(5, 0.3d);


    RegraJuros(Integer multa, double juros) {
        this.juros = juros;
        this.multa = multa;
    }

    private double juros;

    private Integer multa;


    public double getJuros() {
        return juros;
    }

    public Integer getMulta() {
        return multa;
    }
}