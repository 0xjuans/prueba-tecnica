package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Min;

// Request para actualizar solo el stock de un producto.
public class UpdateStockRequest {

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    public UpdateStockRequest() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
