package com.cursoalura.conversor.modelos;

public class Conversion {
    private String monedaOrigen;
    private String monedaDestino;
    private double cantidad;
    private double resultado;

    public Conversion(String monedaOrigen, String monedaDestino, double cantidad, double resultado) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidad = cantidad;
        this.resultado = resultado;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "Conversión: " + cantidad + " " + monedaOrigen + " -> " + resultado + " " + monedaDestino;
    }
}