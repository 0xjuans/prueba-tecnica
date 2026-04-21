package com.juans.prueba_tecnica.domain.model;

// Modelo de dominio que representa una sucursal perteneciente a una franquicia.
public class Branch {

    private String id;
    private String franchiseId;
    private String name;

    public Branch() {
    }

    public Branch(String id, String franchiseId, String name) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.name = name;
    }

    public static Branch createWithoutId(String franchiseId, String name) {
        return new Branch(null, franchiseId, name);
    }

    public String getId() {
        return id;
    }

    public String getFranchiseId() {
        return franchiseId;
    }

    public String getName() {
        return name;
    }

    public Branch withId(String newId) {
        return new Branch(newId, this.franchiseId, this.name);
    }

    public Branch withName(String newName) {
        return new Branch(this.id, this.franchiseId, newName);
    }
}
