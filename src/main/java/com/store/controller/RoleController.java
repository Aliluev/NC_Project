package com.store.controller;

import com.store.exception.ResourceNotFoundException;
import com.store.model.Role;
import com.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RoleController {

    @Autowired
    RoleRepository repository;

    @PostMapping("/role/create")
    public void createUser(@RequestBody Role role){
        repository.save(role);
    }

    @DeleteMapping("/role/delete/{id}")
    public HashMap<String,Boolean> deleteRole(@PathVariable(value = "id") Integer id )throws ResourceNotFoundException{

        Role role=repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Role not found"+ id)
        );
        repository.deleteById(id);

        HashMap<String,Boolean> responce=new HashMap<>();
        responce.put("Deleted",true);
        return responce;
    }



}
