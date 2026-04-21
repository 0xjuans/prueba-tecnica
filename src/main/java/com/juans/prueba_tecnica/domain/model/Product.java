package com.juans.prueba_tecnica.domain.model;

// Modelo de dominio: producto ofertado en una sucursal con stock.
public class Product {

    private String id;
    private String branchId;
    private String name;
    private int stock;

    public Product() {
    }

    public Product(String id, String branchId, String name, int stock) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.stock = stock;
    }

    public static Product createWithoutId(String branchId, String name, int stock) {
        return new Product(null, branchId, name, stock);
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

    public Product withId(String newId) {
        return new Product(newId, this.branchId, this.name, this.stock);
    }

    public Product withStock(int newStock) {
        return new Product(this.id, this.branchId, this.name, newStock);
    }

    public Product withName(String newName) {
        return new Product(this.id, this.branchId, newName, this.stock);
    }
}
