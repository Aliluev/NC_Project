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

    @Autowired
    public StartController(UserRepository userRepository,PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }

    @PostMapping("/api")
    public ResponseEntity startStore() {
        List<Role> roleList=new ArrayList<>();
        Role roleUser=new Role("ROLE_USER");
        Role roleAdmin=new Role("ROLE_ADMIN");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleList.add(roleUser);
        roleList.add(roleAdmin);
        User user=new User("Admin","100","+79999999999","tema@gmail.com",roleList,new ArrayList<Order>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
