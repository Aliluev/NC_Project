package com.store.controller;
import com.store.dto.UserDTO;
import com.store.model.Category;
import com.store.model.Role;
import com.store.model.User;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/get-all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUserDTO() {
        List<User> list= repository.findAll();
        List<UserDTO> userDTOS=new ArrayList<>();

        for(User user: list){
            userDTOS.add(new UserDTO(user));
        }

        return ResponseEntity.ok(userDTOS);

    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){

        User user=new User(userDTO);
        String[] strings=userDTO.getRoles().split(",");
        List<Role> roleList=new ArrayList<>();
        for(String string:strings){
            List<Role> roles=roleRepository.findByName(string);
            if(roles.size()==1){
                roleList.add(roles.get(0));
            }else{
                Role role=new Role(string);
                roleRepository.save(role);
                roleList.add(roleRepository.findByName(string).get(0));
            }
        }

       // User user=new User(userDTO);
        user.setRoles(roleList);
        repository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer id){
      try {
          User user = repository.getById(id);
          UserDTO userDTO = new UserDTO(user);
          return ResponseEntity.ok(userDTO);
      }catch (RuntimeException exception){
          return new ResponseEntity<>(new UserDTO(),HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteUser(@PathVariable(value = "name") String name ){
        try{
            List<User> userList=repository.findByUsername(name);
            repository.delete(userList.get(0));
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new UserDTO(),HttpStatus.NOT_FOUND);
        }
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

    /*
    @PutMapping("/update-user/{name}")
    public ResponseEntity updateUser(@PathVariable(value = "name") String name, @RequestBody UserDTO userDTO){

       try {
           User user = repository.findByUsername(name).get(0);
           // !!!!  Использовать Objects.nonNull(); !!!!
           if (userDTO.getUsername().equals(new String("")) == false) {
               user.setUsername(userDTO.getUsername());
           }
           if (userDTO.getPhone().equals(new String("")) == false) {
               user.setPhone(userDTO.getPhone());
           }
           if (userDTO.getEmail().equals(new String("")) == false) {
               user.setEmail(userDTO.getEmail());
           }
           if (userDTO.getRoles().equals(new String("")) == false) {

               List<Role> listRole = new ArrayList<>();
               String[] strRoles = userDTO.getRoles().split(",");
               for (String str : strRoles) {
                   // Integer.
                   listRole.add(new Role(Integer.getInteger(str)));
               }
               user.setRoles(listRole);
           }

           repository.save(user);
           return ResponseEntity.ok(HttpStatus.OK);
       }catch (RuntimeException runtimeException){
           return new ResponseEntity<>(new UserDTO(),HttpStatus.NOT_FOUND);
       }

    }

     */

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {

       // User user=new User(userDTO);
        String[] strings=userDTO.getRoles().split(",");
        List<Role> roleList=new ArrayList<>();
        for(String string:strings){
            List<Role> roles=roleRepository.findByName(string);
            if(roles.size()==1){
                roleList.add(roles.get(0));
            }else{
                Role role=new Role(string);
                roleRepository.save(role);
                roleList.add(roleRepository.findByName(string).get(0));
            }
        }

        List<User> findUser=repository.findByUsername(userDTO.getUsername());
        //Если имя не новое
        if(findUser.size()>0){
            User user=findUser.get(0);
            user.setUsername(userDTO.getUsername());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setRoles(roleList);
            repository.save(user);
        }else{
            User user=new User(userDTO);
            user.setRoles(roleList);
            repository.save(user);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }




    }
