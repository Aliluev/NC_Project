package com.store.controller;

import com.store.dto.UserDTO;
import com.store.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestController {


    /*
    @GetMapping("/find")
    public Role trueorfalseUser(){
    }

     */

    @GetMapping("/test")
    public String homePage(){
        return "home";
    }

    //Principal - информация о текущем пользователе
    @GetMapping("/auth")
    public String authPage(Principal principal){
        return "secured part of web ";
    }

/*
    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return
                user.getUsername().equals("user") && user.getPassword().equals("password");
    }

 */
/*
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }



 */



/*
    @RequestMapping("/login")
    public boolean login(@RequestBody String username) {
        return
               true;
    }

 */


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "user API";
    }




}
