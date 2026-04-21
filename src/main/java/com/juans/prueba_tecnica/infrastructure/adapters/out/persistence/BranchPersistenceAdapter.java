package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Adapter de persistencia que conecta sucursales con Mongo.
@Component
public class BranchPersistenceAdapter implements BranchRepositoryPort {

    private final BranchMongoRepository branchMongoRepository;

    public BranchPersistenceAdapter(BranchMongoRepository branchMongoRepository) {
        this.branchMongoRepository = branchMongoRepository;
    }

    @Override
    public Flux<Branch> findAll() {
        return branchMongoRepository
                .findAll()
                .map(document -> new Branch(document.getId(), document.getFranchiseId(), document.getName()));
    }

    @Override
    public Flux<Branch> findByFranchiseId(String franchiseId) {
        return branchMongoRepository
                .findByFranchiseId(franchiseId)
                .map(document -> new Branch(document.getId(), document.getFranchiseId(), document.getName()));
    }

    @Override
    public Mono<Branch> findById(String id) {
        return branchMongoRepository
                .findById(id)
                .map(document -> new Branch(document.getId(), document.getFranchiseId(), document.getName()));
    }

    @Override
    public Mono<Branch> save(Branch branch) {
        BranchDocument document = BranchDocument.from(branch.getId(), branch.getFranchiseId(), branch.getName());

        return branchMongoRepository
                .save(document)
                .map(saved -> new Branch(saved.getId(), saved.getFranchiseId(), saved.getName()));
    }
}
