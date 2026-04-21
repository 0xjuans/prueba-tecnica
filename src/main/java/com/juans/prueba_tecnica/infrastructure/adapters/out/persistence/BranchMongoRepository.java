package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

// Repositorio reactivo de Spring Data para sucursales.
public interface BranchMongoRepository extends ReactiveMongoRepository<BranchDocument, String> {

    Flux<BranchDocument> findByFranchiseId(String franchiseId);
}
