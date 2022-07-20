package com.store.controller;

import com.store.dto.UserDTO;
import com.store.model.*;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/start")
public class StartController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    private final String defaultUserRole = "ROLE_USER";
    private final String defaultAdminRole = "ROLE_ADMIN";

    @Autowired
    public StartController(UserRepository userRepository,PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }

    @PostMapping("/api")
    public ResponseEntity startStore() {
        List<Role> roleList=new ArrayList<>();
        List<Role> roleUser = roleRepository.findByName(defaultUserRole);
        if (roleUser.size() == 1) {
            roleList.add(roleUser.get(0));
        } else {
            Role role = new Role(defaultUserRole);
            roleRepository.save(role);
            roleList.add(roleRepository.findByName(defaultUserRole).get(0));
        }
        List<Role> roleAdmin = roleRepository.findByName(defaultAdminRole);
        if (roleAdmin.size() == 1) {
            roleList.add(roleAdmin.get(0));
        } else {
            Role role = new Role(defaultAdminRole);
            roleRepository.save(role);
            roleList.add(roleRepository.findByName(defaultAdminRole).get(0));
        }
        User user=new User("Admin","100","+79999999999","tema@gmail.com",roleList,new ArrayList<Order>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
