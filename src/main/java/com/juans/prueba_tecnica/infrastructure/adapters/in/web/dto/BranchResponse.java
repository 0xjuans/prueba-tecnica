package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

// Response con los datos principales de una sucursal.
public class BranchResponse {

    private String id;
    private String franchiseId;
    private String name;

    public BranchResponse() {
    }

    public BranchResponse(String id, String franchiseId, String name) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.name = name;
    }

    public static BranchResponse from(String id, String franchiseId, String name) {
        return new BranchResponse(id, franchiseId, name);
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
