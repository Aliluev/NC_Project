package com.store.controller;

import com.store.dto.RoleDTO;
import com.store.model.MessageResponse;
import com.store.model.Response;
import com.store.model.Role;
import com.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/role")
public class RoleController {

    private RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository repository) {
        this.roleRepository = repository;
    }

    @GetMapping("/get-all-role")
    public ResponseEntity<List<RoleDTO>> getAllRoleDTO() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleDTO> roleDTOSlist = new ArrayList<>();

        for (Role role : roleList) {
            roleDTOSlist.add(new RoleDTO(role));
        }
        return ResponseEntity.ok(roleDTOSlist);

    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createUser(@RequestBody RoleDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.getName())) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Name of Role is exist"));
        }
        Role role = new Role(roleDTO);
        roleRepository.save(role);
        return new Response().myResponseOK();
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteRole(@PathVariable(value = "name") String name) {
        try {
            List<Role> roleList = roleRepository.findByName(name);
            roleRepository.delete(roleList.get(0));
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new Response().myResponseNotFound(new MessageResponse("Error: Role not found"));
        }
    }

}
