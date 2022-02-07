package com.store.dto;

import com.store.model.Role;
import com.store.model.User;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
    private String name;
    private List<Integer> users;


    public RoleDTO(Role role){
        this.name=role.getName();
        users=new ArrayList<>();
        for(User user: role.getUsers()){
            this.users.add(user.getId());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }





}
