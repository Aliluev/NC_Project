package com.store.controller;
import com.store.dto.UserDTO;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;


    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUserDTO() {
        List<User> list= repository.findAll();
        List<UserDTO> userDTOS=new ArrayList<>();

        for(User user: list){
            userDTOS.add(new UserDTO(user));
        }

        return ResponseEntity.ok(userDTOS);

    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserDTO userDTO){

        User user = new User(userDTO);
        Role role=new Role("guest");
        List<Role> listRole= new ArrayList<Role>();
        listRole.add(role);
        user.setRoles(listRole);
        repository.save(user);
        return user;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer id){
       User user =repository.getById(id);
       UserDTO userDTO=new UserDTO(user);
       return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Integer id ){
        repository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /*
    @PatchMapping("/customer/{id}")
    public HashMap<String, Boolean> updateCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer customer)throws ResourceNotFoundException{
        service.update(id,customer);
        HashMap<String,Boolean> responce=new HashMap<>();
        responce.put("Update", true);
        return responce;
    }

     */

    @PutMapping("/update-user/{id}")
    public void updateUser(@PathVariable(value = "id") Integer id, @RequestBody UserDTO userDTO){
        User user=repository.getById(id);

        // !!!!  Использовать Objects.nonNull(); !!!!
        if(userDTO.getUsername().equals(new String(""))==false){
            user.setUsername(userDTO.getUsername());
        }
        if(userDTO.getPhone().equals(new String(""))==false){
            user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getEmail().equals(new String(""))==false){
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getRoles().equals(new String(""))==false){

            List<Role> listRole=new ArrayList<>();
            String[] strRoles=userDTO.getRoles().split(",");
            for(String str: strRoles){
               // Integer.
                listRole.add(new Role(Integer.getInteger(str)));
            }
            user.setRoles(listRole);
        }

        repository.save(user);


    }



}
