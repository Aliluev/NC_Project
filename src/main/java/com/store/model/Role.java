package com.store.model;

import com.store.dto.RoleDTO;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

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

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public Role(RoleDTO roleDTO) {
        this.id = roleDTO.getId();
        this.name = roleDTO.getName();
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, List<User> users) {
        this.name = name;
    }
}
