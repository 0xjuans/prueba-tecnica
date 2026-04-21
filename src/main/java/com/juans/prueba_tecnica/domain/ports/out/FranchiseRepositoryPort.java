package com.juans.prueba_tecnica.domain.ports.out;

import com.juans.prueba_tecnica.domain.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Puerto de salida para persistir franquicias.
public interface FranchiseRepositoryPort {
    Flux<Franchise> findAll();

    Mono<Franchise> findById(String id);

    Mono<Franchise> save(Franchise franchise);
}