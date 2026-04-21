package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

// Test unitario del caso de uso listar productos con sucursal.
@ExtendWith(MockitoExtension.class)
class ListProductsWithBranchUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private BranchRepositoryPort branchRepositoryPort;

    @InjectMocks
    private ListProductsWithBranchUseCaseImpl useCase;

    @Test
    void shouldIncludeBranchNameWhenExists() {
        when(productRepositoryPort.findAll())
                .thenReturn(Flux.just(new Product("p-1", "b-1", "Pan", 3)));
        when(branchRepositoryPort.findById("b-1"))
                .thenReturn(Mono.just(new Branch("b-1", "f-1", "Sucursal Centro")));

        StepVerifier.create(useCase.listAll())
                .expectNextMatches(row ->
                        "p-1".equals(row.getProductId())
                                && "Sucursal Centro".equals(row.getBranchName()))
                .verifyComplete();
    }

    @Test
    void shouldReturnNullBranchNameWhenBranchNotFound() {
        when(productRepositoryPort.findAll())
                .thenReturn(Flux.just(new Product("p-1", "b-x", "Pan", 3)));
        when(branchRepositoryPort.findById("b-x"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.listAll())
                .expectNextMatches(row -> row.getBranchName() == null)
                .verifyComplete();
    }
}
