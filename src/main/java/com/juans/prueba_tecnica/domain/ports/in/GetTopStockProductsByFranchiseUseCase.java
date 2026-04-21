package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.TopStockProductRow;
import reactor.core.publisher.Flux;

// Consulta el producto con más stock por cada sucursal de una franquicia.
public interface GetTopStockProductsByFranchiseUseCase {
    Flux<TopStockProductRow> execute(String franchiseId);
}
