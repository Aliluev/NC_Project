package com.store.controller;
import com.store.dto.RoleDTO;
import com.store.model.Role;
import com.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository repository;

    @GetMapping("/get-all-role")
    public ResponseEntity<List<RoleDTO>> getAllRoleDTO(){
        List<Role> list= repository.findAll();
        List<RoleDTO> roleDTOS=new ArrayList<>();

        for(Role role: list){
            roleDTOS.add(new RoleDTO(role));
        }
        return ResponseEntity.ok(roleDTOS);

    }

    @PostMapping("/create")
    public Role createUser(@RequestBody RoleDTO roleDTO){
        Role role=new Role(roleDTO);
        repository.save(role);
        return role;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value="id") Integer id){
        Role role =repository.getById(id);

        RoleDTO roleDTO=new RoleDTO(role);
        return ResponseEntity.ok(roleDTO);
    }



    @DeleteMapping("/delete/{name}")
    public void deleteRole(@PathVariable(value = "name") String name ){
        List<Role> list= repository.findAll();
        repository.delete(list.get(0));
        ResponseEntity.ok();
    }




}
