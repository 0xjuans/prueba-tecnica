package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.AddProductToBranchUseCase;
import com.juans.prueba_tecnica.domain.ports.in.DeleteProductFromBranchUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.CreateProductRequest;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// Productos asociados a una sucursal (alta y baja).
@RestController
@RequestMapping("/api/branches/{branchId}/products")
@Tag(name = "Productos", description = "Productos ofertados por sucursal")
public class ProductBranchController {

    private final AddProductToBranchUseCase addProductToBranchUseCase;
    private final DeleteProductFromBranchUseCase deleteProductFromBranchUseCase;

    public ProductBranchController(
            AddProductToBranchUseCase addProductToBranchUseCase,
            DeleteProductFromBranchUseCase deleteProductFromBranchUseCase) {
        this.addProductToBranchUseCase = addProductToBranchUseCase;
        this.deleteProductFromBranchUseCase = deleteProductFromBranchUseCase;
    }

    @PostMapping
    @Operation(summary = "Agregar producto", description = "Crea un producto con nombre y stock en la sucursal")
    public Mono<ResponseEntity<ProductResponse>> create(
            @Parameter(description = "Id de la sucursal") @PathVariable String branchId,
            @Valid @RequestBody CreateProductRequest request) {
        return addProductToBranchUseCase
                .add(branchId, request.getName(), request.getStock())
                .map(product -> ProductResponse.from(product.getId(), product.getBranchId(), product.getName(), product.getStock()))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Eliminar producto", description = "Elimina el producto si pertenece a la sucursal")
    public Mono<ResponseEntity<Void>> delete(
            @Parameter(description = "Id de la sucursal") @PathVariable String branchId,
            @Parameter(description = "Id del producto") @PathVariable String productId) {
        return deleteProductFromBranchUseCase
                .delete(branchId, productId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
