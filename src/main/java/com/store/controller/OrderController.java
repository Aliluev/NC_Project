package com.store.controller;


import com.store.dto.OrderDTO;
import com.store.dto.ProductDTO;
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
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;


    @GetMapping("/get-all")
    public ResponseEntity<List<OrderDTO>> getAllOrderDTO(){
        List<Order> list= orderRepository.findAll();
        List<OrderDTO> orderDTOS=new ArrayList<>();

        for(Order order: list){
            orderDTOS.add(new OrderDTO(order));
        }

        return ResponseEntity.ok(orderDTOS);
    }


    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO){
        User user=userRepository.findByUsername(orderDTO.getUserID()).get(0);
        Status status=statusRepository.findByName(orderDTO.getStatusID()).get(0);

        Order order=new Order();
        order.setUserid(user);
        order.setStatusid(status);
        order.setDate(orderDTO.getDate());
        //order.setId(1);
        orderRepository.save(order);
        //statusRepository.save(status);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Удаление по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable(value = "id") Integer id ) {
        try {
          orderRepository.delete(orderRepository.getById(id));
            return (ResponseEntity.ok(HttpStatus.OK));
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.NOT_FOUND);
        }
    }




}
