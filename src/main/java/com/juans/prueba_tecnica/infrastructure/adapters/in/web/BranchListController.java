package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import com.juans.prueba_tecnica.domain.ports.in.ListBranchesWithFranchiseUseCase;
import com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto.BranchListItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

// Listado global de sucursales con franquicia asociada.
@RestController
@RequestMapping("/api/branches")
@Tag(name = "Sucursales", description = "Listado de sucursales")
public class BranchListController {

    private final ListBranchesWithFranchiseUseCase listBranchesWithFranchiseUseCase;

    public BranchListController(ListBranchesWithFranchiseUseCase listBranchesWithFranchiseUseCase) {
        this.listBranchesWithFranchiseUseCase = listBranchesWithFranchiseUseCase;
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
}
