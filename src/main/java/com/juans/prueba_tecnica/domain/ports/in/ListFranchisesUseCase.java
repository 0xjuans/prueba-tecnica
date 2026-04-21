package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.Franchise;
import reactor.core.publisher.Flux;

// Puerto de entrada para listar todas las franquicias registradas.
public interface ListFranchisesUseCase {
    Flux<Franchise> listAll();
}
