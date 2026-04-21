package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.in.ListFranchisesUseCase;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

// Lista franquicias desde el repositorio (sin filtros).
@Service
public class ListFranchisesUseCaseImpl implements ListFranchisesUseCase {

    private final FranchiseRepositoryPort franchiseRepositoryPort;

    public ListFranchisesUseCaseImpl(FranchiseRepositoryPort franchiseRepositoryPort) {
        this.franchiseRepositoryPort = franchiseRepositoryPort;
    }

    @Override
    public Flux<Franchise> listAll() {
        return franchiseRepositoryPort.findAll();
    }
}
