package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.CreateFranchiseUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.CreateFranchiseRequest;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.FranchiseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// Controlador REST para operaciones de franquicias.
@RestController
@RequestMapping("/api/franchises")
@Tag(name = "Franquicias", description = "Operaciones sobre franquicias")
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;

    public FranchiseController(CreateFranchiseUseCase createFranchiseUseCase) {
        this.createFranchiseUseCase = createFranchiseUseCase;
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
