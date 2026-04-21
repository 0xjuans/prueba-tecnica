package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.UpdateProductNameUseCase;
import com.juans.prueba_tecnica.domain.ports.in.UpdateProductStockUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.ProductResponse;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.UpdateProductNameRequest;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.UpdateStockRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// Actualización de stock por id de producto.
@RestController
@RequestMapping("/api/products")
@Tag(name = "Stock", description = "Actualización de stock de productos")
public class ProductStockController {

    private final UpdateProductNameUseCase updateProductNameUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;

    public ProductStockController(
            UpdateProductNameUseCase updateProductNameUseCase,
            UpdateProductStockUseCase updateProductStockUseCase) {
        this.updateProductNameUseCase = updateProductNameUseCase;
        this.updateProductStockUseCase = updateProductStockUseCase;
    }

    @PatchMapping("/{productId}/name")
    @Operation(summary = "Actualizar nombre de producto", description = "Actualiza el nombre de un producto existente")
    public Mono<ResponseEntity<ProductResponse>> updateName(
            @Parameter(description = "Id del producto") @PathVariable String productId,
            @Valid @RequestBody UpdateProductNameRequest request) {
        return updateProductNameUseCase
                .updateName(productId, request.getName())
                .map(product -> ProductResponse.from(product.getId(), product.getBranchId(), product.getName(), product.getStock()))
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/{productId}/stock")
    @Operation(summary = "Modificar stock", description = "Actualiza la cantidad en stock del producto")
    public Mono<ResponseEntity<ProductResponse>> updateStock(
            @Parameter(description = "Id del producto") @PathVariable String productId,
            @Valid @RequestBody UpdateStockRequest request) {
        return updateProductStockUseCase
                .updateStock(productId, request.getStock())
                .map(product -> ProductResponse.from(product.getId(), product.getBranchId(), product.getName(), product.getStock()))
                .map(ResponseEntity::ok);
    }
}
