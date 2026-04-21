package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

// Repositorio reactivo de Spring Data para sucursales.
public interface BranchMongoRepository extends ReactiveMongoRepository<BranchDocument, String> {
}
