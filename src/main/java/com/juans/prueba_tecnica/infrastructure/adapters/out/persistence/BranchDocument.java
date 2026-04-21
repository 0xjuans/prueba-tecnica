package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Documento Mongo para almacenar sucursales.
@Document(collection = "branches")
public class BranchDocument {

    @Id
    private String id;
    private String franchiseId;
    private String name;

    public BranchDocument() {
    }

    public BranchDocument(String id, String franchiseId, String name) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.name = name;
    }

    public static BranchDocument from(String id, String franchiseId, String name) {
        return new BranchDocument(id, franchiseId, name);
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
}
