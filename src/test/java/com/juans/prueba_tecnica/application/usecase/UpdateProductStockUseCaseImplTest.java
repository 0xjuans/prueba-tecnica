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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Test unitario del caso de uso actualizar stock.
@ExtendWith(MockitoExtension.class)
class UpdateProductStockUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private UpdateProductStockUseCaseImpl useCase;

    @Test
    void shouldUpdateStockWhenProductExists() {
        when(productRepositoryPort.findById("p-1"))
                .thenReturn(Mono.just(new Product("p-1", "br-1", "Pan", 5)));
        when(productRepositoryPort.save(any(Product.class)))
                .thenReturn(Mono.just(new Product("p-1", "br-1", "Pan", 20)));

        StepVerifier.create(useCase.updateStock("p-1", 20))
                .expectNextMatches(product ->
                        "p-1".equals(product.getId()) && product.getStock() == 20)
                .verifyComplete();

        verify(productRepositoryPort).findById("p-1");
        verify(productRepositoryPort).save(any(Product.class));
    }

    @Test
    void shouldFailWhenProductDoesNotExist() {
        when(productRepositoryPort.findById("p-inexistente"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.updateStock("p-inexistente", 10))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException
                                && "Producto no encontrado".equals(error.getMessage()))
                .verify();
    }
}
