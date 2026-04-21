package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Test unitario del caso de uso eliminar producto.
@ExtendWith(MockitoExtension.class)
class DeleteProductFromBranchUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private DeleteProductFromBranchUseCaseImpl useCase;

    @Test
    void shouldDeleteWhenProductBelongsToBranch() {
        when(productRepositoryPort.findById("p-1"))
                .thenReturn(Mono.just(new Product("p-1", "b-1", "Leche", 2)));
        when(productRepositoryPort.deleteById("p-1"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.delete("b-1", "p-1"))
                .verifyComplete();

        verify(productRepositoryPort).deleteById("p-1");
    }

    @Test
    void shouldFailWhenProductBelongsToAnotherBranch() {
        when(productRepositoryPort.findById("p-1"))
                .thenReturn(Mono.just(new Product("p-1", "b-2", "Leche", 2)));

        StepVerifier.create(useCase.delete("b-1", "p-1"))
                .expectErrorMatches(error ->
                        error instanceof BusinessException
                                && "El producto no pertenece a esta sucursal".equals(error.getMessage()))
                .verify();

        verify(productRepositoryPort, never()).deleteById("p-1");
    }
}
