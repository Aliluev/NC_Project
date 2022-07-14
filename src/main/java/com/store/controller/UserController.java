package com.store.controller;

import com.store.dto.UserDTO;
import com.store.model.MessageResponse;
import com.store.model.Response;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository repository, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUserDTO() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userList) {
            userDTOS.add(new UserDTO(user));
        }

        return ResponseEntity.ok(userDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer id) {
        try {
            User user = userRepository.getById(id);
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException exception) {
            logger.info("Error: User Not Found");
            return new Response().myResponseNotFound(new MessageResponse("Error: User Not Found"));
        }
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable(value = "name") String name) {
        try {
            List<User> userList = userRepository.findByUsername(name);
            userRepository.delete(userList.get(0));
            return new Response().myResponseOK();
        } catch (RuntimeException runtimeException) {
            logger.warn("When delete user not found");
         return new Response().myResponseBadRequest(new MessageResponse("Error: Name of User not Found"));
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {

        List<User> userList = userRepository.findByUsername(userDTO.getUsername());
        if (userList.size() == 0) {
            logger.warn("Error: User Not Found");
            return new Response().myResponseNotFound(new MessageResponse("Error: User Not Found"));
        }

        String[] userRoleArray = userDTO.getRoles().split(",");
        List<Role> roleList = new ArrayList<>();
        for (String roleName : userRoleArray) {
            List<Role> roles = roleRepository.findByName(roleName);
            if (roles.size() == 1) {
                roleList.add(roles.get(0));
            } else {
                Role role = new Role(roleName);
                roleRepository.save(role);
                roleList.add(roleRepository.findByName(roleName).get(0));
            }
        }

        userList = userRepository.findByUsername(userDTO.getUsername());
        User user = userList.get(0);
        user.setUsername(userDTO.getUsername());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setRoles(roleList);
        userRepository.save(user);
        return new Response().myResponseOK();
    }

}
