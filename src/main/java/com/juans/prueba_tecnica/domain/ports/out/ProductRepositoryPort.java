package com.juans.prueba_tecnica.domain.ports.out;

import com.juans.prueba_tecnica.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Puerto de salida para persistir productos de sucursal.
public interface ProductRepositoryPort {
    Flux<Product> findByBranchId(String branchId);

    Mono<Product> findById(String id);

    Mono<Product> save(Product product);

    Mono<Void> deleteById(String id);
}
