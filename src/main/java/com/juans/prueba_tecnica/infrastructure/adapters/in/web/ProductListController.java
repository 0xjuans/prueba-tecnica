package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.ListProductsWithBranchUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.ProductListItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

// Listado global de productos con sucursal asociada.
@RestController
@RequestMapping("/api/products")
@Tag(name = "Productos", description = "Listado de productos")
public class ProductListController {

    private final ListProductsWithBranchUseCase listProductsWithBranchUseCase;

    public ProductListController(ListProductsWithBranchUseCase listProductsWithBranchUseCase) {
        this.listProductsWithBranchUseCase = listProductsWithBranchUseCase;
    }

    @GetMapping
    @Operation(
            summary = "Listar productos",
            description = "Devuelve todos los productos con stock y la sucursal a la que pertenecen")
    public Flux<ProductListItemResponse> list() {
        return listProductsWithBranchUseCase
                .listAll()
                .map(row -> ProductListItemResponse.from(
                        row.getProductId(),
                        row.getProductName(),
                        row.getStock(),
                        row.getBranchId(),
                        row.getBranchName()));
    }
}
