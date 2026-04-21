package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Product;
import reactor.core.publisher.Mono;

// Puerto de entrada para actualizar el stock de un producto.
public interface UpdateProductStockUseCase {
    Mono<Product> updateStock(String productId, int newStock);
}
