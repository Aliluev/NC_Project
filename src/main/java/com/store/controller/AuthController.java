package com.store.controller;

import com.store.configs.JwtUtils;
import com.store.model.JwtResponse;
import com.store.model.LoginRequest;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;
import com.store.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRespository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user=userRespository.findByUsername(authentication.getName()).get(0);
      //  UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        /*List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

         */
        List<String> roles =new ArrayList<>();
        for(Role role:user.getRoles()){
            roles.add(role.getName());
        }
       // roles.add("Admin");

        return ResponseEntity.ok(new JwtResponse(jwt,
     user.getId(),user.getUsername(),
     //           userDetails.getId(),
     //           userDetails.getUsername(),
     //           userDetails.getEmail(),
                roles));
    }

}
