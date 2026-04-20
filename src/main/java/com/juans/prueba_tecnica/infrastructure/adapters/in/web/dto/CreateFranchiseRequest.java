package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;

// Request para crear una nueva franquicia.
public class CreateFranchiseRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    public CreateFranchiseRequest() {
    }

    public CreateFranchiseRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Jackson necesita setter (o constructor anotado) para deserializar el JSON del body.
    public void setName(String name) {
        this.name = name;
    }
}
