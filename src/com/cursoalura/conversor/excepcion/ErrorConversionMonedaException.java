package com.cursoalura.conversor.excepcion;

public class ErrorConversionMonedaException extends RuntimeException {
    public ErrorConversionMonedaException(String mensaje) {
        super(mensaje);
    }
}