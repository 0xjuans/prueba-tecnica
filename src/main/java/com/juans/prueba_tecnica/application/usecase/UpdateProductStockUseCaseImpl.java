package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.in.UpdateProductStockUseCase;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.BusinessException;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Actualiza stock validando existencia y reglas de negocio.
@Service
public class UpdateProductStockUseCaseImpl implements UpdateProductStockUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public UpdateProductStockUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Mono<Product> updateStock(String productId, int newStock) {
        if (productId == null || productId.isBlank()) {
            return Mono.error(new BusinessException("El id del producto es obligatorio"));
        }
        if (newStock < 0) {
            return Mono.error(new BusinessException("El stock no puede ser negativo"));
        }

        return productRepositoryPort
                .findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado")))
                .flatMap(product -> productRepositoryPort.save(product.withStock(newStock)));
    }
}
