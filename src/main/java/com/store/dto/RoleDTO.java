package com.store.dto;
import com.store.model.Role;


public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO(Role role){
        this.name=role.getName();
        this.id=role.getId();
    }


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


    /*
    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }


     */


    /*
    public RoleDTO(Role role){
        this.name=role.getName();
        users=new ArrayList<>();
        for(User user: role.getUsers()){
            this.users.add(user.getId());
        }
    }

 */


}
