package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.AddBranchToFranchiseUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.BranchResponse;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.CreateBranchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// Controlador REST para sucursales asociadas a una franquicia.
@RestController
@RequestMapping("/api/franchises/{franchiseId}/branches")
@Tag(name = "Sucursales", description = "Operaciones sobre sucursales de una franquicia")
public class BranchController {

    private final AddBranchToFranchiseUseCase addBranchToFranchiseUseCase;

    public BranchController(AddBranchToFranchiseUseCase addBranchToFranchiseUseCase) {
        this.addBranchToFranchiseUseCase = addBranchToFranchiseUseCase;
    }

    @PostMapping
    @Operation(summary = "Agregar sucursal", description = "Crea una sucursal asociada a la franquicia indicada")
    public Mono<ResponseEntity<BranchResponse>> create(
            @Parameter(description = "Id de la franquicia") @PathVariable String franchiseId,
            @Valid @RequestBody CreateBranchRequest request) {
        return addBranchToFranchiseUseCase
                .add(franchiseId, request.getName())
                .map(branch -> BranchResponse.from(branch.getId(), branch.getFranchiseId(), branch.getName()))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
}
