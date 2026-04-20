package com.juans.prueba_tecnica.infrastructure.adapters.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Documento Mongo para almacenar franquicias.
@Document(collection = "franchises")
public class FranchiseDocument {

    @Id
    private String id;
    private String name;

    public FranchiseDocument() {
    }

    public FranchiseDocument(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FranchiseDocument from(String id, String name) {
        return new FranchiseDocument(id, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}