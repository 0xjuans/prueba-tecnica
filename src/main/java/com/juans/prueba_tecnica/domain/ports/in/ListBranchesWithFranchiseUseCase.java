package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.BranchWithFranchise;
import reactor.core.publisher.Flux;

// Lista todas las sucursales con id y nombre de la franquicia asociada.
public interface ListBranchesWithFranchiseUseCase {
    Flux<BranchWithFranchise> listAll();
}
