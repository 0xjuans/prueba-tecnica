package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
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

// Test unitario del caso de uso actualizar nombre de producto.
@ExtendWith(MockitoExtension.class)
class UpdateProductNameUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private UpdateProductNameUseCaseImpl useCase;

    @Test
    void shouldUpdateNameWhenProductExists() {
        when(productRepositoryPort.findById("p-1"))
                .thenReturn(Mono.just(new Product("p-1", "b-1", "Viejo", 1)));
        when(productRepositoryPort.save(any(Product.class)))
                .thenReturn(Mono.just(new Product("p-1", "b-1", "Nuevo", 1)));

        StepVerifier.create(useCase.updateName("p-1", "Nuevo"))
                .expectNextMatches(p -> "Nuevo".equals(p.getName()))
                .verifyComplete();
    }

    @Test
    void shouldFailWhenProductNotFound() {
        when(productRepositoryPort.findById("p-x"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.updateName("p-x", "Nuevo"))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException
                                && "Producto no encontrado".equals(error.getMessage()))
                .verify();
    }
}
