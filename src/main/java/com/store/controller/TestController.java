package com.store.controller;

import com.store.model.Role;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
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


}
