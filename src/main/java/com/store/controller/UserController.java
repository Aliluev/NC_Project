package com.store.controller;

import com.store.dto.UserDTO;
import com.store.model.MessageResponse;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {


    UserRepository repository;
    RoleRepository roleRepository;


    @Autowired
    public UserController(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUserDTO() {
        List<User> list = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : list) {
            userDTOS.add(new UserDTO(user));
        }

        return ResponseEntity.ok(userDTOS);

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer id) {
        try {
            User user = repository.getById(id);
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(new UserDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable(value = "name") String name) {
        try {
            List<User> userList = repository.findByUsername(name);
            repository.delete(userList.get(0));
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException runtimeException) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name of User not Found"));
        }
    }


    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {

        List<User> findUser = repository.findByUsername(userDTO.getUsername());
        if (findUser.size() == 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User Not Found"));
        }


        String[] strings = userDTO.getRoles().split(",");
        List<Role> roleList = new ArrayList<>();
        for (String string : strings) {
            List<Role> roles = roleRepository.findByName(string);
            if (roles.size() == 1) {
                roleList.add(roles.get(0));
            } else {
                Role role = new Role(string);
                roleRepository.save(role);
                roleList.add(roleRepository.findByName(string).get(0));
            }
        }

        findUser = repository.findByUsername(userDTO.getUsername());
        User user = findUser.get(0);
        user.setUsername(userDTO.getUsername());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setRoles(roleList);
        repository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
