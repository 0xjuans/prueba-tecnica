package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Test unitario del caso de uso agregar sucursal.
@ExtendWith(MockitoExtension.class)
class AddBranchToFranchiseUseCaseImplTest {

    @Mock
    private FranchiseRepositoryPort franchiseRepositoryPort;

    @Mock
    private BranchRepositoryPort branchRepositoryPort;

    @InjectMocks
    private AddBranchToFranchiseUseCaseImpl useCase;

    @Test
    void shouldCreateBranchWhenFranchiseExists() {
        when(franchiseRepositoryPort.findById("fr-1"))
                .thenReturn(Mono.just(new Franchise("fr-1", "Franquicia Centro")));
        when(branchRepositoryPort.save(any(Branch.class)))
                .thenReturn(Mono.just(new Branch("br-1", "fr-1", "Sucursal Centro")));

        StepVerifier.create(useCase.add("fr-1", "Sucursal Centro"))
                .expectNextMatches(branch ->
                        "br-1".equals(branch.getId())
                                && "fr-1".equals(branch.getFranchiseId())
                                && "Sucursal Centro".equals(branch.getName()))
                .verifyComplete();

        verify(franchiseRepositoryPort).findById("fr-1");
        verify(branchRepositoryPort).save(any(Branch.class));
    }

    @Test
    void shouldFailWhenFranchiseDoesNotExist() {
        when(franchiseRepositoryPort.findById("fr-inexistente"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.add("fr-inexistente", "Sucursal X"))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException
                                && "Franquicia no encontrada".equals(error.getMessage()))
                .verify();

        verify(branchRepositoryPort, never()).save(any(Branch.class));
    }
}
