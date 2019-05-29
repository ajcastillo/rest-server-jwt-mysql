package com.example.restserver.domain;

import javax.persistence.Entity;

@Entity
public class Rol {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
