package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.in.UpdateProductNameUseCase;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Actualiza nombre de producto validando existencia y reglas básicas.
@Service
public class UpdateProductNameUseCaseImpl implements UpdateProductNameUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public UpdateProductNameUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Mono<Product> updateName(String productId, String newName) {
        if (productId == null || productId.isBlank()) {
            return Mono.error(new BusinessException("El id del producto es obligatorio"));
        }
        if (newName == null || newName.trim().isEmpty()) {
            return Mono.error(new BusinessException("El nuevo nombre del producto es obligatorio"));
        }

        return productRepositoryPort
                .findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado")))
                .flatMap(product -> productRepositoryPort.save(product.withName(newName.trim())));
    }
}
