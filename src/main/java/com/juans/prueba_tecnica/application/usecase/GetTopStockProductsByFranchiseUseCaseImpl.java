package com.juans.prueba_tecnica.application.usecase;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.model.TopStockProductRow;
import com.juans.prueba_tecnica.domain.ports.in.GetTopStockProductsByFranchiseUseCase;
import com.juans.prueba_tecnica.domain.ports.out.BranchRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.FranchiseRepositoryPort;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import com.juans.prueba_tecnica.shared.exception.ResourceNotFoundException;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Por cada sucursal de la franquicia, elige el producto con mayor stock (desempate por nombre).
@Service
public class GetTopStockProductsByFranchiseUseCaseImpl implements GetTopStockProductsByFranchiseUseCase {

    private final FranchiseRepositoryPort franchiseRepositoryPort;
    private final BranchRepositoryPort branchRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public GetTopStockProductsByFranchiseUseCaseImpl(
            FranchiseRepositoryPort franchiseRepositoryPort,
            BranchRepositoryPort branchRepositoryPort,
            ProductRepositoryPort productRepositoryPort) {
        this.franchiseRepositoryPort = franchiseRepositoryPort;
        this.branchRepositoryPort = branchRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Flux<TopStockProductRow> execute(String franchiseId) {
        return franchiseRepositoryPort
                .findById(franchiseId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Franquicia no encontrada")))
                .flatMapMany(franchise -> branchRepositoryPort.findByFranchiseId(franchise.getId()))
                .concatMap(branch -> productRepositoryPort
                        .findByBranchId(branch.getId())
                        .collectList()
                        .flatMapMany(products -> toRowFlux(branch.getId(), branch.getName(), products)));
    }

    private static Flux<TopStockProductRow> toRowFlux(String branchId, String branchName, List<Product> products) {
        if (products.isEmpty()) {
            return Flux.empty();
        }
        Product best = products.stream()
                .max(Comparator.comparingInt(Product::getStock).thenComparing(Product::getName))
                .orElseThrow();
        return Flux.just(new TopStockProductRow(
                branchId, branchName, best.getId(), best.getName(), best.getStock()));
    }
}
