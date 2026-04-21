package com.juans.prueba_tecnica.domain.ports.in;

import reactor.core.publisher.Mono;

// Puerto de entrada para eliminar un producto de una sucursal.
public interface DeleteProductFromBranchUseCase {
    Mono<Void> delete(String branchId, String productId);
}
