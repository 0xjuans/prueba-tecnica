package com.juans.prueba_tecnica.shared.exception;

// Indica que un recurso solicitado no existe (p. ej. franquicia o sucursal inexistente).
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
