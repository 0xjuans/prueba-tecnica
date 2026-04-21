package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.model.ProductWithBranch;
import com.juans.prueba_tecnica.domain.ports.in.ListProductsWithBranchUseCase;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Enriquece cada producto con el nombre de su sucursal.
@Service
public class ListProductsWithBranchUseCaseImpl implements ListProductsWithBranchUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final BranchRepositoryPort branchRepositoryPort;

    public ListProductsWithBranchUseCaseImpl(
            ProductRepositoryPort productRepositoryPort,
            BranchRepositoryPort branchRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.branchRepositoryPort = branchRepositoryPort;
    }

    @Override
    public Flux<ProductWithBranch> listAll() {
        return productRepositoryPort.findAll().concatMap(this::withBranchName);
    }

    private Mono<ProductWithBranch> withBranchName(Product product) {
        return branchRepositoryPort
                .findById(product.getBranchId())
                .map(branch -> new ProductWithBranch(
                        product.getId(),
                        product.getName(),
                        product.getStock(),
                        branch.getId(),
                        branch.getName()))
                .switchIfEmpty(Mono.fromSupplier(() -> new ProductWithBranch(
                        product.getId(),
                        product.getName(),
                        product.getStock(),
                        product.getBranchId(),
                        null)));
    }
}
