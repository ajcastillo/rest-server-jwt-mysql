package com.example.restserver.domain;

import javax.persistence.*;
import java.util.Set;

public class User {
    private String userName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "name")
    )
    private Set<Rol> roles;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
