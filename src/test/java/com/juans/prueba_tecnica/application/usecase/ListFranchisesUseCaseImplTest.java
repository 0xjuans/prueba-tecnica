package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Test unitario del caso de uso listar franquicias.
@ExtendWith(MockitoExtension.class)
class ListFranchisesUseCaseImplTest {

    @Mock
    private FranchiseRepositoryPort franchiseRepositoryPort;

    @InjectMocks
    private ListFranchisesUseCaseImpl useCase;

    @Test
    void shouldReturnAllFranchises() {
        when(franchiseRepositoryPort.findAll()).thenReturn(Flux.just(
                new Franchise("f-1", "Franquicia A"),
                new Franchise("f-2", "Franquicia B")));

        StepVerifier.create(useCase.listAll())
                .expectNextMatches(f -> "f-1".equals(f.getId()))
                .expectNextMatches(f -> "f-2".equals(f.getId()))
                .verifyComplete();

        verify(franchiseRepositoryPort).findAll();
    }
}
