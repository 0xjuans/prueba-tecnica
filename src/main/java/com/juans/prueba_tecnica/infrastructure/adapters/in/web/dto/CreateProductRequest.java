package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// Request para crear un producto en una sucursal.
public class CreateProductRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    public CreateProductRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
