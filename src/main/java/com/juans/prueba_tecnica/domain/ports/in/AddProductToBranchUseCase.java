package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Product;
import reactor.core.publisher.Mono;

// Puerto de entrada para agregar un producto a una sucursal.
public interface AddProductToBranchUseCase {
    Mono<Product> add(String branchId, String name, int stock);
}
