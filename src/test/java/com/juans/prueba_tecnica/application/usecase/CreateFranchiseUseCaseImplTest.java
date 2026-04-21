package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
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

// Test unitario del caso de uso crear franquicia.
@ExtendWith(MockitoExtension.class)
class CreateFranchiseUseCaseImplTest {

    @Mock
    private FranchiseRepositoryPort franchiseRepositoryPort;

    @InjectMocks
    private CreateFranchiseUseCaseImpl useCase;

    @Test
    void shouldCreateFranchiseWhenNameIsValid() {
        when(franchiseRepositoryPort.save(any(Franchise.class)))
                .thenReturn(Mono.just(new Franchise("f-1", "Franquicia Norte")));

        StepVerifier.create(useCase.create("Franquicia Norte"))
                .expectNextMatches(franchise ->
                        "f-1".equals(franchise.getId()) && "Franquicia Norte".equals(franchise.getName()))
                .verifyComplete();

        verify(franchiseRepositoryPort).save(any(Franchise.class));
    }

    @Test
    void shouldFailWhenNameIsBlank() {
        StepVerifier.create(useCase.create("   "))
                .expectErrorMatches(error ->
                        error instanceof BusinessException
                                && "El nombre de la franquicia es obligatorio".equals(error.getMessage()))
                .verify();

        verify(franchiseRepositoryPort, never()).save(any(Franchise.class));
    }
}
