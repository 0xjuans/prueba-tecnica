package com.juans.prueba_tecnica.domain.model;

// Vista de lectura: producto con datos de la sucursal asociada.
public class ProductWithBranch {

    private final String productId;
    private final String productName;
    private final int stock;
    private final String branchId;
    private final String branchName;

    public ProductWithBranch(
            String productId, String productName, int stock, String branchId, String branchName) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.branchId = branchId;
        this.branchName = branchName;
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
