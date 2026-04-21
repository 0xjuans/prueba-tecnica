package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.CreateFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.in.GetTopStockProductsByFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.in.ListFranchisesUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.CreateFranchiseRequest;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.FranchiseResponse;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.TopStockByBranchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Controlador REST para operaciones de franquicias.
@RestController
@RequestMapping("/api/franchises")
@Tag(name = "Franquicias", description = "Operaciones sobre franquicias")
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final ListFranchisesUseCase listFranchisesUseCase;
    private final GetTopStockProductsByFranchiseUseCase getTopStockProductsByFranchiseUseCase;

    public FranchiseController(
            CreateFranchiseUseCase createFranchiseUseCase,
            ListFranchisesUseCase listFranchisesUseCase,
            GetTopStockProductsByFranchiseUseCase getTopStockProductsByFranchiseUseCase) {
        this.createFranchiseUseCase = createFranchiseUseCase;
        this.listFranchisesUseCase = listFranchisesUseCase;
        this.getTopStockProductsByFranchiseUseCase = getTopStockProductsByFranchiseUseCase;
    }

    @GetMapping
    @Operation(summary = "Listar franquicias", description = "Devuelve todas las franquicias con su id y nombre")
    public Flux<FranchiseResponse> list() {
        return listFranchisesUseCase
                .listAll()
                .map(franchise -> FranchiseResponse.from(franchise.getId(), franchise.getName()));
    }

    @GetMapping("/{franchiseId}/top-stock-by-branch")
    @Operation(
            summary = "Top stock por sucursal",
            description = "Por cada sucursal con productos, devuelve el producto con mayor stock y a qué sucursal pertenece")
    public Flux<TopStockByBranchResponse> topStockByBranch(
            @Parameter(description = "Id de la franquicia") @PathVariable String franchiseId) {
        return getTopStockProductsByFranchiseUseCase
                .execute(franchiseId)
                .map(row -> TopStockByBranchResponse.from(
                        row.getBranchId(),
                        row.getBranchName(),
                        row.getProductId(),
                        row.getProductName(),
                        row.getStock()));
    }

    @PostMapping
    @Operation(summary = "Crear franquicia", description = "Crea una nueva franquicia con su nombre")
    public Mono<ResponseEntity<FranchiseResponse>> create(@Valid @RequestBody CreateFranchiseRequest request) {
        return createFranchiseUseCase
                .create(request.getName())
                .map(franchise -> FranchiseResponse.from(franchise.getId(), franchise.getName()))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
}
