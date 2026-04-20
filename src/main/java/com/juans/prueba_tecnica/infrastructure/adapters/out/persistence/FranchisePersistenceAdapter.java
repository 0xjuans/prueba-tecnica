package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

// Adapter de persistencia que conecta dominio con Mongo.
@Component
public class FranchisePersistenceAdapter implements FranchiseRepositoryPort {

    private final FranchiseMongoRepository franchiseMongoRepository;

    public FranchisePersistenceAdapter(FranchiseMongoRepository franchiseMongoRepository) {
        this.franchiseMongoRepository = franchiseMongoRepository;
    }

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        FranchiseDocument document = FranchiseDocument.from(franchise.getId(), franchise.getName());

        return franchiseMongoRepository
                .save(document)
                .map(saved -> new Franchise(saved.getId(), saved.getName()));
    }
}