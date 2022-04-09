package com.store.controller;

import com.store.dto.AddProductDTO;
import com.store.dto.OrderListDTO;
import com.store.model.*;
import com.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order-list")
public class OrderListController {

    private final String plannedStatus = "planned";

    private OrderListRepository orderListRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private StatusRepository statusRepository;

    @Autowired
    public OrderListController(OrderListRepository orderListRepository, ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.orderListRepository = orderListRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProductFromOrderList(@RequestBody AddProductDTO addProductDTO) {
        OrderList orderList = new OrderList();
        Product product = this.productRepository.findByName(addProductDTO.getProductName()).get(0);
        User user = userRepository.findByUsername(addProductDTO.getUserName()).get(0);

        try {
            Integer checkInt = Integer.parseInt(addProductDTO.getCount());
        } catch (RuntimeException exception) {
            return new Response().myResponseBadRequest(new MessageResponse("Wrong input count"));
        }

        if (statusRepository.getByName(plannedStatus).size() == 0) {
            Status status = new Status();
            status.setName(plannedStatus);
            statusRepository.save(status);
        }

        Status status = statusRepository.getByName(plannedStatus).get(0);
        List<Order> orderLists = orderRepository.getByUseridAndStatusid(user, status);
        if (orderLists.size() == 0) {
            Order order = new Order();
            order.setUserid(user);
            order.setStatusid(status);
            order.setDate(new Date().toString());
            orderRepository.save(order);
        }
        Order order = orderRepository.getByUseridAndStatusid(user, status).get(0);


        List<OrderList> orderDetailsList = orderListRepository.findByOrderIDAndProductID(order, product);
        if (orderDetailsList.size() == 1) {
            OrderList productOrder = orderDetailsList.get(0);
            productOrder.setCount(productOrder.getCount() + Integer.parseInt(addProductDTO.getCount()));
            if ((productOrder.getCount() > product.getCount()) || (productOrder.getCount() == 0)) {
                return new Response().myResponseBadRequest(new MessageResponse("Count product is less then ordered"));
            }
            orderListRepository.save(productOrder);
        } else {
            orderList.setOrderID(order);
            orderList.setProductID(product);
            orderList.setCount(Integer.parseInt(addProductDTO.getCount()));
            if ((orderList.getCount() > product.getCount()) || (orderList.getCount() == 0)) {

                return new Response().myResponseBadRequest(new MessageResponse("Count product is less then ordered"));
            }
            orderListRepository.save(orderList);
        }
        return new Response().myResponseOK();
    }


    @GetMapping("/get-order-list/{name}")
    public ResponseEntity<List<OrderListDTO>> getOrderListByUserName(@PathVariable(value = "name") String name) {
        User user = userRepository.getByUsername(name).get(0);
        Status status = statusRepository.getByName(plannedStatus).get(0);
        if (orderRepository.getByUseridAndStatusid(user, status).size() == 0) {
            Order order = new Order();
            order.setUserid(user);
            order.setStatusid(status);
            order.setDate(new Date().toString());
        }

        List<Order> orderList = orderRepository.getByUseridAndStatusid(user, status);
        if (orderList.size() == 0) {
            return ResponseEntity.ok(new ArrayList<OrderListDTO>());
        }
        Order order = orderRepository.getByUseridAndStatusid(user, status).get(0);
        return ResponseEntity.ok(getOrderDTO(order));

    }

    @GetMapping("/get-order-list-ordered/{id}")
    public ResponseEntity<List<OrderListDTO>> getOrderedOrderListByUserName(@PathVariable(value = "id") String id) {
        Order order = orderRepository.getById(Integer.parseInt(id));
        return ResponseEntity.ok(getOrderDTO(order));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Integer id) {
        try {
            OrderList orderList = orderListRepository.getById(id);
            orderListRepository.delete(orderList);
            return new Response().myResponseOK();
        } catch (RuntimeException exception) {
            return new Response().myResponseNotFound(new MessageResponse("ID not found"));
        }
    }


    private List<OrderListDTO> getOrderDTO(Order order) {
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

}
