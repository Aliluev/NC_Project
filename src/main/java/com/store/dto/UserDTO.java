package com.store.dto;


import com.store.model.Role;
import com.store.model.User;

import java.util.List;

public class UserDTO {

    private String username;
    private String phone;
    private String email;
    private List<Role> roles;

    public UserDTO(){}

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
