package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

// Test unitario del caso de uso listar sucursales con franquicia.
@ExtendWith(MockitoExtension.class)
class ListBranchesWithFranchiseUseCaseImplTest {

    @Mock
    private BranchRepositoryPort branchRepositoryPort;

    @Mock
    private FranchiseRepositoryPort franchiseRepositoryPort;

    @InjectMocks
    private ListBranchesWithFranchiseUseCaseImpl useCase;

    @Test
    void shouldIncludeFranchiseNameWhenExists() {
        when(branchRepositoryPort.findAll())
                .thenReturn(Flux.just(new Branch("b-1", "f-1", "Sucursal Norte")));
        when(franchiseRepositoryPort.findById("f-1"))
                .thenReturn(Mono.just(new Franchise("f-1", "Franquicia Norte")));

        StepVerifier.create(useCase.listAll())
                .expectNextMatches(row ->
                        "b-1".equals(row.getBranchId())
                                && "Franquicia Norte".equals(row.getFranchiseName()))
                .verifyComplete();
    }

    @Test
    void shouldReturnNullFranchiseNameWhenNotFound() {
        when(branchRepositoryPort.findAll())
                .thenReturn(Flux.just(new Branch("b-1", "f-x", "Sucursal Norte")));
        when(franchiseRepositoryPort.findById("f-x"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.listAll())
                .expectNextMatches(row -> row.getFranchiseName() == null)
                .verifyComplete();
    }
}
