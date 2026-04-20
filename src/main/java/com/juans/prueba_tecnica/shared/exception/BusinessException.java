package com.juans.prueba_tecnica.shared.exception;

// Excepción de negocio para reglas de validación del dominio.
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}