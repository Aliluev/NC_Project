package com.store.controller;

import com.store.dto.RoleDTO;
import com.store.dto.UserDTO;
import com.store.exception.ResourceNotFoundException;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;


    @PostMapping("/create")
    public User createUser(@RequestBody UserDTO userDTO){

        User user = new User(userDTO.getUsername(), userDTO.getPhone(), userDTO.getEmail());
        Role role=new Role("guest");
        List<Role> listRole= new ArrayList<Role>();
        listRole.add(role);
        user.setRoles(listRole);
        /*
        User user =new User();
        user.setId(15);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
       // user.setRoles(userDTO.getRoles());

        Role role=new Role()
        List<Role> roles=new ArrayList<Role>();
        for(Integer integer:userDTO.getRoles()){
            roles.add(integer)
        }
       user.setRoles(userDTO.getRoles());



         */


       repository.save(user);
       return user;
    }



    /*
    @PostMapping("/create")
    public User createUser(@RequestBody Model model){
        User user=new User();
        user.setId(15);
        user.setUsername(model.);
    }

     */



    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer id)throws ResourceNotFoundException{
       User user =repository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("not found"+ id)
       );

       UserDTO userDTO=new UserDTO(user);
       return ResponseEntity.ok().body(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id )throws ResourceNotFoundException {
        repository.deleteById(id);
        ResponseEntity.ok();
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
