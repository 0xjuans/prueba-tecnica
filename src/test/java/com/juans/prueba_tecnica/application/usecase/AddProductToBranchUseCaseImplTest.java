package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
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

// Test unitario del caso de uso agregar producto.
@ExtendWith(MockitoExtension.class)
class AddProductToBranchUseCaseImplTest {

    @Mock
    private BranchRepositoryPort branchRepositoryPort;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private AddProductToBranchUseCaseImpl useCase;

    @Test
    void shouldCreateProductWhenInputIsValid() {
        when(branchRepositoryPort.findById("br-1"))
                .thenReturn(Mono.just(new Branch("br-1", "fr-1", "Sucursal Norte")));
        when(productRepositoryPort.save(any(Product.class)))
                .thenReturn(Mono.just(new Product("p-1", "br-1", "Leche", 12)));

        StepVerifier.create(useCase.add("br-1", "Leche", 12))
                .expectNextMatches(product ->
                        "p-1".equals(product.getId())
                                && "Leche".equals(product.getName())
                                && product.getStock() == 12)
                .verifyComplete();

        verify(productRepositoryPort).save(any(Product.class));
    }

    @Test
    void shouldFailWhenStockIsNegative() {
        StepVerifier.create(useCase.add("br-1", "Leche", -1))
                .expectErrorMatches(error ->
                        error instanceof BusinessException
                                && "El stock no puede ser negativo".equals(error.getMessage()))
                .verify();

        verify(branchRepositoryPort, never()).findById(any());
        verify(productRepositoryPort, never()).save(any());
    }
}
