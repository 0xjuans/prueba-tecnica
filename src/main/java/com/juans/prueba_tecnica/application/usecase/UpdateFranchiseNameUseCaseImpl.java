package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.in.UpdateFranchiseNameUseCase;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Actualiza el nombre de la franquicia validando existencia y reglas básicas.
@Service
public class UpdateFranchiseNameUseCaseImpl implements UpdateFranchiseNameUseCase {

    private final FranchiseRepositoryPort franchiseRepositoryPort;

    public UpdateFranchiseNameUseCaseImpl(FranchiseRepositoryPort franchiseRepositoryPort) {
        this.franchiseRepositoryPort = franchiseRepositoryPort;
    }

    @Override
    public Mono<Franchise> updateName(String franchiseId, String newName) {
        if (franchiseId == null || franchiseId.isBlank()) {
            return Mono.error(new BusinessException("El id de la franquicia es obligatorio"));
        }
        if (newName == null || newName.trim().isEmpty()) {
            return Mono.error(new BusinessException("El nuevo nombre de la franquicia es obligatorio"));
        }

        return franchiseRepositoryPort
                .findById(franchiseId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Franquicia no encontrada")))
                .flatMap(franchise -> franchiseRepositoryPort.save(franchise.withName(newName.trim())));
    }
}
