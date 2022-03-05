package com.store.controller;

import com.store.dto.OrderListDTO;
import com.store.model.Order;
import com.store.model.OrderList;
import com.store.model.Product;
import com.store.repository.OrderListRepository;
import com.store.repository.OrderRepository;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order-list")
public class OrderListController {


    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;


    @PostMapping("/create")
    public ResponseEntity createOrderList(@RequestBody OrderListDTO orderListDTO) {
        OrderList orderList=new OrderList();
        Product product=productRepository.findById(orderListDTO.getProductID()).get();
        Order order=orderRepository.findById(orderListDTO.getOrderID()).get();
        orderList.setCount(orderListDTO.getCount());
        orderList.setProductID(product);
        orderList.setOrderID(order);
        orderListRepository.save(orderList);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
