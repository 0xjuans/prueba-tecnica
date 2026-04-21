package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.model.BranchWithFranchise;
import com.juans.prueba_tecnica.domain.ports.in.ListBranchesWithFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Enriquece cada sucursal con el nombre de su franquicia.
@Service
public class ListBranchesWithFranchiseUseCaseImpl implements ListBranchesWithFranchiseUseCase {

    private final BranchRepositoryPort branchRepositoryPort;
    private final FranchiseRepositoryPort franchiseRepositoryPort;

    public ListBranchesWithFranchiseUseCaseImpl(
            BranchRepositoryPort branchRepositoryPort,
            FranchiseRepositoryPort franchiseRepositoryPort) {
        this.branchRepositoryPort = branchRepositoryPort;
        this.franchiseRepositoryPort = franchiseRepositoryPort;
    }

    @Override
    public Flux<BranchWithFranchise> listAll() {
        return branchRepositoryPort.findAll().concatMap(this::withFranchiseName);
    }

    private Mono<BranchWithFranchise> withFranchiseName(Branch branch) {
        return franchiseRepositoryPort
                .findById(branch.getFranchiseId())
                .map(franchise -> new BranchWithFranchise(
                        branch.getId(),
                        branch.getName(),
                        franchise.getId(),
                        franchise.getName()))
                .switchIfEmpty(Mono.fromSupplier(() -> new BranchWithFranchise(
                        branch.getId(),
                        branch.getName(),
                        branch.getFranchiseId(),
                        null)));
    }
}
