package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Franchise;
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
import static org.mockito.Mockito.when;

// Test unitario del caso de uso actualizar nombre de franquicia.
@ExtendWith(MockitoExtension.class)
class UpdateFranchiseNameUseCaseImplTest {

    @Mock
    private FranchiseRepositoryPort franchiseRepositoryPort;

    @InjectMocks
    private UpdateFranchiseNameUseCaseImpl useCase;

    @Test
    void shouldUpdateNameWhenFranchiseExists() {
        when(franchiseRepositoryPort.findById("f-1"))
                .thenReturn(Mono.just(new Franchise("f-1", "Viejo")));
        when(franchiseRepositoryPort.save(any(Franchise.class)))
                .thenReturn(Mono.just(new Franchise("f-1", "Nuevo")));

        StepVerifier.create(useCase.updateName("f-1", "Nuevo"))
                .expectNextMatches(f -> "Nuevo".equals(f.getName()))
                .verifyComplete();
    }

    @Test
    void shouldFailWhenFranchiseNotFound() {
        when(franchiseRepositoryPort.findById("f-x"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.updateName("f-x", "Nuevo"))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException
                                && "Franquicia no encontrada".equals(error.getMessage()))
                .verify();
    }
}
