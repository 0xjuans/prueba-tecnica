package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Branch;
import com.juans.prueba_tecnica.domain.model.Franchise;
import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

// Test unitario del caso de uso top stock por sucursal.
@ExtendWith(MockitoExtension.class)
class GetTopStockProductsByFranchiseUseCaseImplTest {

    @Mock
    private FranchiseRepositoryPort franchiseRepositoryPort;

    @Mock
    private BranchRepositoryPort branchRepositoryPort;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private GetTopStockProductsByFranchiseUseCaseImpl useCase;

    @Test
    void shouldReturnTopProductPerBranch() {
        when(franchiseRepositoryPort.findById("f-1"))
                .thenReturn(Mono.just(new Franchise("f-1", "Franquicia Uno")));
        when(branchRepositoryPort.findByFranchiseId("f-1"))
                .thenReturn(Flux.just(new Branch("b-1", "f-1", "Sucursal A")));
        when(productRepositoryPort.findByBranchId("b-1"))
                .thenReturn(Flux.just(
                        new Product("p-1", "b-1", "A", 3),
                        new Product("p-2", "b-1", "B", 9)));

        StepVerifier.create(useCase.execute("f-1"))
                .expectNextMatches(row ->
                        "b-1".equals(row.getBranchId())
                                && "p-2".equals(row.getProductId())
                                && row.getStock() == 9)
                .verifyComplete();
    }

    @Test
    void shouldFailWhenFranchiseNotFound() {
        when(franchiseRepositoryPort.findById("f-x"))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute("f-x"))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException
                                && "Franquicia no encontrada".equals(error.getMessage()))
                .verify();
    }
}
