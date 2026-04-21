package com.juans.prueba_tecnica.infrastructure.adapters.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// Endpoint raiz para exponer informacion basica y enlaces utiles.
@RestController
@Tag(name = "Root", description = "Informacion basica de la API")
public class RootController {

    @GetMapping("/")
    @Operation(summary = "Inicio", description = "Devuelve estado simple y enlaces de documentacion")
    public Mono<Map<String, String>> home() {
        return Mono.just(Map.of(
                "message", "API de franquicias operativa",
                "swagger", "/swagger-ui.html",
                "openapi", "/api-docs"));
    }
}
