package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Test unitario del caso de uso actualizar nombre de sucursal.
@ExtendWith(MockitoExtension.class)
class UpdateBranchNameUseCaseImplTest {

    @Mock
    private BranchRepositoryPort branchRepositoryPort;

    @InjectMocks
    private UpdateBranchNameUseCaseImpl useCase;

    @Test
    void shouldUpdateNameWhenBranchExists() {
        when(branchRepositoryPort.findById("b-1"))
                .thenReturn(Mono.just(new Branch("b-1", "f-1", "Vieja")));
        when(branchRepositoryPort.save(any(Branch.class)))
                .thenReturn(Mono.just(new Branch("b-1", "f-1", "Nueva")));

        StepVerifier.create(useCase.updateName("b-1", "Nueva"))
                .expectNextMatches(b -> "Nueva".equals(b.getName()))
                .verifyComplete();
    }

    @Test
    void shouldFailWhenBranchNotFound() {
        when(branchRepositoryPort.findById("b-x"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.updateName("b-x", "Nueva"))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException
                                && "Sucursal no encontrada".equals(error.getMessage()))
                .verify();
    }
}
