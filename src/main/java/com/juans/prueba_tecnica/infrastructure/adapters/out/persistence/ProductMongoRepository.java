package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

// Repositorio reactivo de Spring Data para productos.
public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {

    Flux<ProductDocument> findByBranchId(String branchId);
}
