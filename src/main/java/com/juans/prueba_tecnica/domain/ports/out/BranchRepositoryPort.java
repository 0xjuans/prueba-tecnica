package com.juans.prueba_tecnica.domain.ports.out;

import com.juans.prueba_tecnica.domain.model.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Puerto de salida para persistir sucursales.
public interface BranchRepositoryPort {
    Flux<Branch> findByFranchiseId(String franchiseId);

    Mono<Branch> findById(String id);

    Mono<Branch> save(Branch branch);
}
