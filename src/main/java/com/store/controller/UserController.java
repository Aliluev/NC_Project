package com.store.controller;

import com.store.dto.UserDTO;
import com.store.exception.ResourceNotFoundException;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer id)throws ResourceNotFoundException{
       User user =repository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("not found"+ id)
       );

       UserDTO userDTO=new UserDTO(user);
       return ResponseEntity.ok().body(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id )throws ResourceNotFoundException {
        repository.deleteById(id);
        ResponseEntity.ok();
    }

}
