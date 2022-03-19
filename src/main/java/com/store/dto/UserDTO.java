package com.store.dto;

import com.store.model.Role;
import com.store.model.User;

import java.util.Objects;

public class UserDTO {

    private String username;
    private String phone;
    private String email;
    private String roles;


    public UserDTO() {
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        StringBuilder strRoles = new StringBuilder();
        int i = 0;

        for (Role role : user.getRoles()) {
            if (i != 0) {
                strRoles.append(",");
            }
            strRoles.append(role.getName());
            i++;
        }
        roles = strRoles.toString();


    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) && Objects.equals(phone, userDTO.phone) && Objects.equals(email, userDTO.email) && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, phone, email, roles);
    }
}
