package com.store.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="roleid"),
            inverseJoinColumns = @JoinColumn(name = "userid"))
    private List<Role> roles;

}
