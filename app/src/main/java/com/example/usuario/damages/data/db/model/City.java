package com.example.usuario.damages.data.db.model;

/**
 * Created by usuario on 21/02/18.
 */

public class City {
    int id;
    String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
