package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.ports.in.AddBranchToFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Valida franquicia existente y persiste la sucursal.
@Service
public class AddBranchToFranchiseUseCaseImpl implements AddBranchToFranchiseUseCase {

    private final FranchiseRepositoryPort franchiseRepositoryPort;
    private final BranchRepositoryPort branchRepositoryPort;

    public AddBranchToFranchiseUseCaseImpl(
            FranchiseRepositoryPort franchiseRepositoryPort,
            BranchRepositoryPort branchRepositoryPort) {
        this.franchiseRepositoryPort = franchiseRepositoryPort;
        this.branchRepositoryPort = branchRepositoryPort;
    }

    @Override
    public Mono<Branch> add(String franchiseId, String name) {
        if (franchiseId == null || franchiseId.isBlank()) {
            return Mono.error(new BusinessException("El id de la franquicia es obligatorio"));
        }
        if (name == null || name.trim().isEmpty()) {
            return Mono.error(new BusinessException("El nombre de la sucursal es obligatorio"));
        }

        return franchiseRepositoryPort
                .findById(franchiseId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Franquicia no encontrada")))
                .flatMap(franchise -> branchRepositoryPort.save(Branch.createWithoutId(franchise.getId(), name.trim())));
    }
}
