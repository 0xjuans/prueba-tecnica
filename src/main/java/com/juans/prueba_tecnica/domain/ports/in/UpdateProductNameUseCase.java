package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Product;
import reactor.core.publisher.Mono;

// Puerto de entrada para actualizar el nombre de un producto.
public interface UpdateProductNameUseCase {
    Mono<Product> updateName(String productId, String newName);
}
