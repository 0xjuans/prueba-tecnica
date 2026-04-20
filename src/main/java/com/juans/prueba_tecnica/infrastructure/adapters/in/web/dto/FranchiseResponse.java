package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

// Response con los datos principales de una franquicia.
public class FranchiseResponse {

    private String id;
    private String name;

    public FranchiseResponse() {
    }

    public FranchiseResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FranchiseResponse from(String id, String name) {
        return new FranchiseResponse(id, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
