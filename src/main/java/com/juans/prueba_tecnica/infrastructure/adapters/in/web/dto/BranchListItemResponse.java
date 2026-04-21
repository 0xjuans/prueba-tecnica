package com.juans.prueba_tecnica.infrastructure.adapters.in.web.dto;

// Respuesta de listado: sucursal + franquicia asociada.
public class BranchListItemResponse {

    private String branchId;
    private String branchName;
    private String franchiseId;
    private String franchiseName;

    public BranchListItemResponse() {
    }

    public BranchListItemResponse(String branchId, String branchName, String franchiseId, String franchiseName) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
    }

    public static BranchListItemResponse from(
            String branchId, String branchName, String franchiseId, String franchiseName) {
        return new BranchListItemResponse(branchId, branchName, franchiseId, franchiseName);
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getFranchiseId() {
        return franchiseId;
    }

    public String getFranchiseName() {
        return franchiseName;
    }
}
