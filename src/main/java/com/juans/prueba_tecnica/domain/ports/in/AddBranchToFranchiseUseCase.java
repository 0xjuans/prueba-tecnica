package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Branch;
import reactor.core.publisher.Mono;

// Puerto de entrada para agregar una sucursal a una franquicia existente.
public interface AddBranchToFranchiseUseCase {
    Mono<Branch> add(String franchiseId, String name);
}
