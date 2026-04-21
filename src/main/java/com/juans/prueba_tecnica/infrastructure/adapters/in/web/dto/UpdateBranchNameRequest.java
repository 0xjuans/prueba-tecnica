package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;

// Request para actualizar el nombre de una sucursal.
public class UpdateBranchNameRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    public UpdateBranchNameRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
