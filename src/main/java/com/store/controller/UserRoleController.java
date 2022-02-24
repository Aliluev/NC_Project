package com.store.controller;
import com.store.dto.UserDTO;
import com.store.model.User;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    UserRepository repository;

    @GetMapping("/get-all-user-role")
    public List<UserDTO> getAllUserDTO() {
     //   return (repository.getAllByUsernameAndRoles());
       List<User> list= repository.findAll();
       List<UserDTO> userDTOS=new ArrayList<>();

       for(User user: list){
           userDTOS.add(new UserDTO(user));
       }
       return userDTOS;
        /*
        UserDTO userDTO=new UserDTO(user);
        return ResponseEntity.ok().body(userDTO);

         */
    }


}
