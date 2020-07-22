package com.softserve.edu.entity;

public class Entity {
    private int id;
    private static int entityIdCounter = 1;
    private String name;

    public Entity(String name) {
        this.id = entityIdCounter++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
