package com.store.controller;

import com.store.dto.StatusDTO;
import com.store.model.*;
import com.store.repository.OrderRepository;
import com.store.repository.ProductRepository;
import com.store.repository.StatusRepository;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/status")
public class StatusController {

    private StatusRepository statusRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    private final String plannedStatus = "planned";
    private final String proccesedStatus = "proccesed";

    @Autowired
    public StatusController(StatusRepository statusRepository, OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.statusRepository = statusRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StatusDTO>> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        List<StatusDTO> statusDTOS = new ArrayList<>();

        for (Status status : statusList) {
            statusDTOS.add(new StatusDTO(status.getName()));
        }

        return ResponseEntity.ok(statusDTOS);

    }

    @PostMapping("/status-ordered")
    public ResponseEntity nextStatus(@RequestBody String name) {
        User user = userRepository.getByUsername(name).get(0);
        Order order;
        try {
            order = orderRepository.getByUseridAndStatusid(user, statusRepository.getByName(plannedStatus).get(0)).get(0);
        } catch (RuntimeException exception) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Didn't order anything"));
        }
        List<OrderList> orderDetails = order.getOrderLists();
        if (orderDetails.size() == 0) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Product not ordered"));
        }

        for (OrderList orderList : orderDetails) {
            Product product = orderList.getProductID();
            if (product.getCount() < orderList.getCount()) {
                return new Response().myResponseBadRequest(new MessageResponse("Error:" + product.getName() + " in store has chanched, please remove the item"));
            }
            product.setCount(product.getCount() - orderList.getCount());
            productRepository.save(product);
        }
        if (statusRepository.getByName(proccesedStatus).size() == 0) {
            Status status = new Status();
            status.setName(proccesedStatus);
            statusRepository.save(status);
        }
        Status status = statusRepository.getByName(proccesedStatus).get(0);
        order.setStatusid(status);
        order.setDate(new Date().toString());
        orderRepository.save(order);
        return ResponseEntity.ok(status.getName());

    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createStatus(@RequestBody StatusDTO statusDTO) {
        Status status = new Status(statusDTO);
        statusRepository.save(status);
        return new Response().myResponseOK();
    }


    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable(value = "name") String name) {
        try {
            statusRepository.delete(statusRepository.findByName(name).get(0));
            return new Response().myResponseOK();
        } catch (RuntimeException exception) {
            return new Response().myResponseNotFound(new MessageResponse("Product not found"));
        }
    }

}
