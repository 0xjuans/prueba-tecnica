package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

// Repositorio reactivo de Spring Data para franquicias.
public interface FranchiseMongoRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}