package com.store.controller;

import com.store.dto.RoleDTO;
import com.store.exception.ResourceNotFoundException;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository repository;

    @PostMapping("/create")
    public void createUser(@RequestBody Role role){
        repository.save(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value="id") Integer id)throws ResourceNotFoundException{
        Role role =repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("not found"+ id)
        );
        
        RoleDTO roleDTO=new RoleDTO(role);
        return ResponseEntity.ok().body(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable(value = "id") Integer id )throws ResourceNotFoundException{
        repository.deleteById(id);
        ResponseEntity.ok();
    }



}
