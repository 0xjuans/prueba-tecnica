package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.in.AddProductToBranchUseCase;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Valida sucursal y reglas de producto antes de persistir.
@Service
public class AddProductToBranchUseCaseImpl implements AddProductToBranchUseCase {

    private final BranchRepositoryPort branchRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public AddProductToBranchUseCaseImpl(
            BranchRepositoryPort branchRepositoryPort,
            ProductRepositoryPort productRepositoryPort) {
        this.branchRepositoryPort = branchRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Mono<Product> add(String branchId, String name, int stock) {
        if (branchId == null || branchId.isBlank()) {
            return Mono.error(new BusinessException("El id de la sucursal es obligatorio"));
        }
        if (name == null || name.trim().isEmpty()) {
            return Mono.error(new BusinessException("El nombre del producto es obligatorio"));
        }
        if (stock < 0) {
            return Mono.error(new BusinessException("El stock no puede ser negativo"));
        }

        return branchRepositoryPort
                .findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Sucursal no encontrada")))
                .flatMap(branch -> productRepositoryPort.save(Product.createWithoutId(branch.getId(), name.trim(), stock)));
    }
}
