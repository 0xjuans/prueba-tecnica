package com.juans.prueba_tecnica.domain.ports.out;

import com.juans.prueba_tecnica.domain.model.Franchise;
import reactor.core.publisher.Mono;

// Puerto de salida para persistir franquicias.
public interface FranchiseRepositoryPort {
    Mono<Franchise> save(Franchise franchise);
}