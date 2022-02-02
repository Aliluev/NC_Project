package com.store.controller;

import com.store.exception.ResourceNotFoundException;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping("/user/create")
    public void createUser(@RequestBody User user){
        repository.save(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public HashMap<String,Boolean> deleteUser(@PathVariable(value = "id") Integer id )throws ResourceNotFoundException {

        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role not found" + id)
        );
        repository.deleteById(id);

        HashMap<String, Boolean> responce = new HashMap<>();
        responce.put("Deleted", true);
        return responce;
    }

}
