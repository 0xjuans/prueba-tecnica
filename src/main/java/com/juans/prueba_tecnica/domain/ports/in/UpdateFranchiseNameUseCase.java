package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Franchise;
import reactor.core.publisher.Mono;

// Puerto de entrada para actualizar el nombre de una franquicia.
public interface UpdateFranchiseNameUseCase {
    Mono<Franchise> updateName(String franchiseId, String newName);
}
