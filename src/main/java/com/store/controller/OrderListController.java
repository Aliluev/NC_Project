package com.store.controller;

import com.store.dto.AddProductDTO;
import com.store.dto.OrderListDTO;
import com.store.model.*;
import com.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order-list")
public class OrderListController {


    OrderListRepository orderListRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    UserRepository userRepository;
    StatusRepository statusRepository;

    @Autowired
    public OrderListController(OrderListRepository orderListRepository, ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.orderListRepository = orderListRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProductfromOrderList(@RequestBody AddProductDTO addProductDTO) {
        OrderList orderList = new OrderList();
        Product product = productRepository.findByName(addProductDTO.getProductName()).get(0);
        User user = userRepository.findByUsername(addProductDTO.getUserName()).get(0);

        try {
            Integer checkInt = Integer.parseInt(addProductDTO.getCount());
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Wrong input count", HttpStatus.BAD_REQUEST);
        }

        if (statusRepository.getByName("begin").size() == 0) {
            Status status = new Status();
            status.setName("begin");
            statusRepository.save(status);
        }

        Status status = statusRepository.getByName("begin").get(0);
        List<Order> orderLists = orderRepository.getByUseridAndStatusid(user, status);
        if (orderLists.size() == 0) {
            Order order = new Order();
            order.setUserid(user);
            order.setStatusid(status);
            order.setDate(new Date().toString());
            orderRepository.save(order);
        }
        Order order = orderRepository.getByUseridAndStatusid(user, status).get(0);


        List<OrderList> orderListOsmotr = orderListRepository.findByOrderIDAndProductID(order, product);
        if (orderListOsmotr.size() == 1) {
            OrderList orderList1 = orderListOsmotr.get(0);
            orderList1.setCount(orderList1.getCount() + Integer.parseInt(addProductDTO.getCount()));
            if ((orderList1.getCount() > product.getCount()) || (orderList1.getCount() == 0)) {
                return new ResponseEntity<>(
                        "Count product is less then ordered",
                        HttpStatus.BAD_REQUEST);
            }
            orderListRepository.save(orderList1);
        } else {
            orderList.setOrderID(order);
            orderList.setProductID(product);
            orderList.setCount(Integer.parseInt(addProductDTO.getCount()));
            if ((orderList.getCount() > product.getCount()) || (orderList.getCount() == 0)) {
                return new ResponseEntity<>(
                        "Count product is less then ordered",
                        HttpStatus.BAD_REQUEST);
            }
            orderListRepository.save(orderList);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/get-order-list/{name}")
    public List<OrderListDTO> getOrderListByUserName(@PathVariable(value = "name") String name) {
        User user = userRepository.getByUsername(name).get(0);
        Status status = statusRepository.getByName("begin").get(0);
        if (orderRepository.getByUseridAndStatusid(user, status).size() == 0) {
            Order order = new Order();
            order.setUserid(user);
            order.setStatusid(status);
            order.setDate(new Date().toString());
        }

        List<Order> orderList = orderRepository.getByUseridAndStatusid(user, status);
        if (orderList.size() == 0) {
            return new ArrayList<OrderListDTO>();
        }
        Order order = orderRepository.getByUseridAndStatusid(user, status).get(0);

        List<OrderList> orderLists = orderListRepository.findByOrderID(order);
        List<OrderListDTO> orderListDTOS = new ArrayList<>();
        for (OrderList orderList1 : orderLists) {
            OrderListDTO orderListDTO = new OrderListDTO();
            orderListDTO.setOrderID(orderList1.getOrderID().getId().toString());
            orderListDTO.setOrderListId(orderList1.getId().toString());
            orderListDTO.setProductName(orderList1.getProductID().getName());
            orderListDTO.setCount(orderList1.getCount().toString());
            orderListDTOS.add(orderListDTO);
        }
        return orderListDTOS;

    }

    @GetMapping("/get-order-list-ordered/{id}")
    public List<OrderListDTO> getOrderedOrderListByUserName(@PathVariable(value = "id") String id) {

        Order order = orderRepository.getById(Integer.parseInt(id));
        List<OrderList> orderLists = orderListRepository.findByOrderID(order);
        List<OrderListDTO> orderListDTOS = new ArrayList<>();
        for (OrderList orderList1 : orderLists) {
            OrderListDTO orderListDTO = new OrderListDTO();
            orderListDTO.setOrderID(orderList1.getOrderID().getId().toString());
            orderListDTO.setOrderListId(orderList1.getId().toString());
            orderListDTO.setProductName(orderList1.getProductID().getName());
            orderListDTO.setCount(orderList1.getCount().toString());
            orderListDTOS.add(orderListDTO);
        }
        return orderListDTOS;

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Integer id) {
        try {
            OrderList orderList = orderListRepository.getById(id);
            orderListRepository.delete(orderList);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
        }
    }


}
