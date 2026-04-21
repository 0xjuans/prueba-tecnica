package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.ports.in.DeleteProductFromBranchUseCase;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Elimina producto solo si pertenece a la sucursal indicada.
@Service
public class DeleteProductFromBranchUseCaseImpl implements DeleteProductFromBranchUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductFromBranchUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Mono<Void> delete(String branchId, String productId) {
        if (branchId == null || branchId.isBlank() || productId == null || productId.isBlank()) {
            return Mono.error(new BusinessException("branchId y productId son obligatorios"));
        }

        return productRepositoryPort
                .findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado")))
                .flatMap(product -> {
                    if (!branchId.equals(product.getBranchId())) {
                        return Mono.error(new BusinessException("El producto no pertenece a esta sucursal"));
                    }
                    return productRepositoryPort.deleteById(productId);
                });
    }
}
