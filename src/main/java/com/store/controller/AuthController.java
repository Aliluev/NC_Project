package com.store.controller;

import com.store.configs.JwtUtils;
import com.store.dto.UserDTO;
import com.store.model.*;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;
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


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userRespository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        User user=new User(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        List<Role> roleList=new ArrayList<>();

        String string="ROLE_USER";
        List<Role> roles=roleRepository.findByName(string);
        if(roles.size()==1){
            roleList.add(roles.get(0));
        }else{
            Role role=new Role(string);
            roleRepository.save(role);
            roleList.add(roleRepository.findByName(string).get(0));
        }
        /*
        for(String string:signupRequest.getRoles()){
            List<Role> roles=roleRepository.findByName(string);
            if(roles.size()==1){
                roleList.add(roles.get(0));
            }else{
                Role role=new Role(string);
                roleRepository.save(role);
                roleList.add(roleRepository.findByName(string).get(0));
            }
        }

         */
        user.setRoles(roleList);
        userRespository.save(user);

        return ResponseEntity.ok().body(new UserDTO(user));





    }





}
