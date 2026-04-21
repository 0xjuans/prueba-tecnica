package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.ports.in.UpdateBranchNameUseCase;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Actualiza el nombre de la sucursal validando existencia y reglas básicas.
@Service
public class UpdateBranchNameUseCaseImpl implements UpdateBranchNameUseCase {

    private final BranchRepositoryPort branchRepositoryPort;

    public UpdateBranchNameUseCaseImpl(BranchRepositoryPort branchRepositoryPort) {
        this.branchRepositoryPort = branchRepositoryPort;
    }

    @Override
    public Mono<Branch> updateName(String branchId, String newName) {
        if (branchId == null || branchId.isBlank()) {
            return Mono.error(new BusinessException("El id de la sucursal es obligatorio"));
        }
        if (newName == null || newName.trim().isEmpty()) {
            return Mono.error(new BusinessException("El nuevo nombre de la sucursal es obligatorio"));
        }

        return branchRepositoryPort
                .findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Sucursal no encontrada")))
                .flatMap(branch -> branchRepositoryPort.save(branch.withName(newName.trim())));
    }
}
