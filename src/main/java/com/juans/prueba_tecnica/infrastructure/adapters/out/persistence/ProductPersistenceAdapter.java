package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import com.juans.prueba_tecnica.domain.model.Product;
import com.juans.prueba_tecnica.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Adapter de persistencia para productos en Mongo.
@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductMongoRepository productMongoRepository;

    public ProductPersistenceAdapter(ProductMongoRepository productMongoRepository) {
        this.productMongoRepository = productMongoRepository;
    }

    @Override
    public Flux<Product> findByBranchId(String branchId) {
        return productMongoRepository
                .findByBranchId(branchId)
                .map(document -> new Product(document.getId(), document.getBranchId(), document.getName(), document.getStock()));
    }

    @Override
    public Mono<Product> findById(String id) {
        return productMongoRepository
                .findById(id)
                .map(document -> new Product(document.getId(), document.getBranchId(), document.getName(), document.getStock()));
    }

    @Override
    public Mono<Product> save(Product product) {
        ProductDocument document = ProductDocument.from(
                product.getId(), product.getBranchId(), product.getName(), product.getStock());

        return productMongoRepository
                .save(document)
                .map(saved -> new Product(saved.getId(), saved.getBranchId(), saved.getName(), saved.getStock()));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return productMongoRepository.deleteById(id);
    }
}
