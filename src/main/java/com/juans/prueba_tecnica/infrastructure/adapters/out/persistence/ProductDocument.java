package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Documento Mongo para productos por sucursal.
@Document(collection = "products")
public class ProductDocument {

    @Id
    private String id;
    private String branchId;
    private String name;
    private int stock;

    public ProductDocument() {
    }

    public ProductDocument(String id, String branchId, String name, int stock) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.stock = stock;
    }

    public static ProductDocument from(String id, String branchId, String name, int stock) {
        return new ProductDocument(id, branchId, name, stock);
    }

    public String getId() {
        return id;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }
}
