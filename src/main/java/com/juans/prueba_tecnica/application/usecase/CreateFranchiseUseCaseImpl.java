package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.in.CreateFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Implementa la regla de negocio para crear una franquicia.
@Service
public class CreateFranchiseUseCaseImpl implements CreateFranchiseUseCase {

    private final FranchiseRepositoryPort franchiseRepositoryPort;

    public CreateFranchiseUseCaseImpl(FranchiseRepositoryPort franchiseRepositoryPort) {
        this.franchiseRepositoryPort = franchiseRepositoryPort;
    }

    @Override
    public Mono<Franchise> create(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Mono.error(new BusinessException("El nombre de la franquicia es obligatorio"));
        }

        Franchise franchise = Franchise.createWithoutId(name.trim());
        return franchiseRepositoryPort.save(franchise);
    }
}