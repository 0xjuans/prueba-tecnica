package com.juans.prueba_tecnica.domain.model;

// Modelo de dominio que representa una franquicia.
public class Franchise {

    private String id;
    private String name;

    public Franchise() {
    }

    public Franchise(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Franchise createWithoutId(String name) {
        return new Franchise(null, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Franchise withId(String newId) {
        return new Franchise(newId, this.name);
    }

    public Franchise withName(String newName) {
        return new Franchise(this.id, newName);
    }
}