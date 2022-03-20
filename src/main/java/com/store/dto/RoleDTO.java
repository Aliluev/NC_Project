package com.store.dto;

import com.store.model.Role;


public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO(Role role) {
        this.name = role.getName();
        this.id = role.getId();
    }

    public RoleDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
