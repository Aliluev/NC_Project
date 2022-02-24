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

    @PostMapping("/create")
    public void createUser(@RequestBody Role role){
        repository.save(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value="id") Integer id){
        Role role =repository.getById(id);

        RoleDTO roleDTO=new RoleDTO(role);
        return ResponseEntity.ok().body(roleDTO);
    }



    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable(value = "id") Integer id ){
        repository.deleteById(id);
        ResponseEntity.ok();
    }

    @GetMapping("/get-all-role")
    public List<RoleDTO> getAllRoleDTO(){
        List<Role> list= repository.findAll();
        List<RoleDTO> roleDTOS=new ArrayList<>();

        for(Role role: list){
            roleDTOS.add(new RoleDTO(role));
        }
        return roleDTOS;

    }


}
