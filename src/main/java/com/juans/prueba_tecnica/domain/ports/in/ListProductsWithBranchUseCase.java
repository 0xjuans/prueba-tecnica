package com.juans.prueba_tecnica.domain.ports.in;

import com.juans.prueba_tecnica.domain.model.ProductWithBranch;
import reactor.core.publisher.Flux;

// Lista todos los productos con id y nombre de la sucursal asociada.
public interface ListProductsWithBranchUseCase {
    Flux<ProductWithBranch> listAll();
}
