package com.juans.prueba_tecnica.domain.model;

// Vista de lectura: sucursal con datos de la franquicia asociada.
public class BranchWithFranchise {

    private final String branchId;
    private final String branchName;
    private final String franchiseId;
    private final String franchiseName;

    public BranchWithFranchise(String branchId, String branchName, String franchiseId, String franchiseName) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
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
