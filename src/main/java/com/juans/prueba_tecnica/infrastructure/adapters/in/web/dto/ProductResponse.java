package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

// Response con datos de un producto.
public class ProductResponse {

    private String id;
    private String branchId;
    private String name;
    private int stock;

    public ProductResponse() {
    }

    public ProductResponse(String id, String branchId, String name, int stock) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.stock = stock;
    }

    public static ProductResponse from(String id, String branchId, String name, int stock) {
        return new ProductResponse(id, branchId, name, stock);
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
