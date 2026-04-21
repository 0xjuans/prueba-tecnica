package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Branch;
import reactor.core.publisher.Mono;

// Puerto de entrada para actualizar el nombre de una sucursal.
public interface UpdateBranchNameUseCase {
    Mono<Branch> updateName(String branchId, String newName);
}
