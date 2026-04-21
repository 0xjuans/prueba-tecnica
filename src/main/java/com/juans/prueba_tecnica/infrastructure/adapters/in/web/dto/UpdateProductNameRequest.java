package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;

// Request para actualizar el nombre de un producto.
public class UpdateProductNameRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    public UpdateProductNameRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
