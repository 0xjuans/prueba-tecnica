package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Franchise;
import reactor.core.publisher.Mono;

// Puerto de entrada para crear una franquicia.
public interface CreateFranchiseUseCase {
    Mono<Franchise> create(String name);
}