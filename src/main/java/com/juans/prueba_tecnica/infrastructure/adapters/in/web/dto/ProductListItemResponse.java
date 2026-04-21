package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

// Respuesta de listado: producto + sucursal asociada.
public class ProductListItemResponse {

    private String productId;
    private String productName;
    private int stock;
    private String branchId;
    private String branchName;

    public ProductListItemResponse() {
    }

    public ProductListItemResponse(
            String productId, String productName, int stock, String branchId, String branchName) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public static ProductListItemResponse from(
            String productId, String productName, int stock, String branchId, String branchName) {
        return new ProductListItemResponse(productId, productName, stock, branchId, branchName);
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }
}
