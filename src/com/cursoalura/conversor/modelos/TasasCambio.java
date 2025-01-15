package com.cursoalura.conversor.modelos;

import java.util.Map;

public class TasasCambio {
    private String base; // Moneda base
    private Map<String, Double> rates; // Tasas de cambio para otras monedas

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
