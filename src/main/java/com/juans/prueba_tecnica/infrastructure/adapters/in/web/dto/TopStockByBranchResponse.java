package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

// Respuesta: producto con más stock en una sucursal.
public class TopStockByBranchResponse {

    private String branchId;
    private String branchName;
    private String productId;
    private String productName;
    private int stock;

    public TopStockByBranchResponse() {
    }

    public TopStockByBranchResponse(String branchId, String branchName, String productId, String productName, int stock) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
    }

    public static TopStockByBranchResponse from(
            String branchId, String branchName, String productId, String productName, int stock) {
        return new TopStockByBranchResponse(branchId, branchName, productId, productName, stock);
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
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
}
