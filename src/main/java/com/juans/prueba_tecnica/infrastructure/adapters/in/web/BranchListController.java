package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.ListBranchesWithFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.in.UpdateBranchNameUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.BranchListItemResponse;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.BranchResponse;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.UpdateBranchNameRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Listado global de sucursales con franquicia asociada.
@RestController
@RequestMapping("/api/branches")
@Tag(name = "Sucursales", description = "Listado de sucursales")
public class BranchListController {

    private final ListBranchesWithFranchiseUseCase listBranchesWithFranchiseUseCase;
    private final UpdateBranchNameUseCase updateBranchNameUseCase;

    public BranchListController(
            ListBranchesWithFranchiseUseCase listBranchesWithFranchiseUseCase,
            UpdateBranchNameUseCase updateBranchNameUseCase) {
        this.listBranchesWithFranchiseUseCase = listBranchesWithFranchiseUseCase;
        this.updateBranchNameUseCase = updateBranchNameUseCase;
    }

    @GetMapping
    @Operation(
            summary = "Listar sucursales",
            description = "Devuelve todas las sucursales con id/nombre y la franquicia a la que pertenecen")
    public Flux<BranchListItemResponse> list() {
        return listBranchesWithFranchiseUseCase
                .listAll()
                .map(row -> BranchListItemResponse.from(
                        row.getBranchId(),
                        row.getBranchName(),
                        row.getFranchiseId(),
                        row.getFranchiseName()));
    }

    @PatchMapping("/{branchId}/name")
    @Operation(summary = "Actualizar nombre de sucursal", description = "Actualiza el nombre de una sucursal existente")
    public Mono<ResponseEntity<BranchResponse>> updateName(
            @Parameter(description = "Id de la sucursal") @PathVariable String branchId,
            @Valid @RequestBody UpdateBranchNameRequest request) {
        return updateBranchNameUseCase
                .updateName(branchId, request.getName())
                .map(branch -> BranchResponse.from(branch.getId(), branch.getFranchiseId(), branch.getName()))
                .map(ResponseEntity::ok);
    }
}
