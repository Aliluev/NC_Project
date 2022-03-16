package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.dto.StatusDTO;
import com.store.model.Order;
import com.store.model.Product;
import com.store.model.Status;
import com.store.model.User;
import com.store.repository.OrderRepository;
import com.store.repository.StatusRepository;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<StatusDTO>> getAllStatus() {
        List<Status> list= statusRepository.findAll();
        List<StatusDTO> statusDTOS=new ArrayList<>();

        for(Status status: list){
            statusDTOS.add(new StatusDTO(status.getName()));
        }

        return ResponseEntity.ok(statusDTOS);

    }

    @PostMapping("/status-ordered")
    public ResponseEntity nextstatus(@RequestBody String name ) {

        User user=userRepository.getByUsername(name).get(0);
        Order order=orderRepository.getByUseridAndStatusid(user,statusRepository.getByName("begin").get(0)).get(0);

        if(statusRepository.getByName("ordered").size()==0){
            Status status=new Status();
            status.setName("ordered");
            statusRepository.save(status);
        }
        Status status=statusRepository.getByName("ordered").get(0);
        order.setStatusid(status);
        order.setDate(new Date().toString());
        orderRepository.save(order);
        return ResponseEntity.ok(status.getName());

    }



    @PostMapping("/create")
    public ResponseEntity createStatus(@RequestBody StatusDTO statusDTO){
        Status status=new Status(statusDTO);
    statusRepository.save(status);
    return ResponseEntity.ok(HttpStatus.OK);
    }

    //Удаление по названию
    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteProduct(@PathVariable(value = "name") String name ) {
        try {
            statusRepository.delete(statusRepository.findByName(name).get(0));
            return (ResponseEntity.ok(HttpStatus.OK));
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.NOT_FOUND);
        }
    }


}
