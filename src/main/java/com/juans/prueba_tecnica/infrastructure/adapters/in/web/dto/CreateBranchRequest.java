package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;

// Request para crear una sucursal bajo una franquicia.
public class CreateBranchRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    public CreateBranchRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
