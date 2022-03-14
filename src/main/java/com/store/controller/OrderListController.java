package com.store.controller;

import com.store.dto.AddProductDTO;
import com.store.dto.OrderListDTO;
import com.store.dto.ProductDTO;
import com.store.dto.UserDTO;
import com.store.model.Order;
import com.store.model.OrderList;
import com.store.model.Product;
import com.store.model.User;
import com.store.repository.OrderListRepository;
import com.store.repository.OrderRepository;
import com.store.repository.ProductRepository;
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
@RequestMapping("/order-list")
public class OrderListController {


    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

/*
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

 */

    @PostMapping("/add-product")
    public ResponseEntity addProductfromOrderList(@RequestBody AddProductDTO addProductDTO) {
        OrderList orderList=new OrderList();
        Product product=productRepository.findByName(addProductDTO.getProductName()).get(0);
        User user=userRepository.findByUsername(addProductDTO.getUserName()).get(0);
      //  Order order=orderRepository.findByUserid(user.getId());
        Order order=orderRepository.findByUserid(user).get(0);
      // Order order=new Order();
        if(order==null){
            order=new Order();
            order.setUserid(user);
            order.setDate(new Date().toString());
            orderRepository.save(order);
            order=orderRepository.findByUserid(user).get(0);
        }
       orderList.setOrderID(order);
        orderList.setProductID(product);
        orderList.setCount(Integer.parseInt(addProductDTO.getCount()));
        orderListRepository.save(orderList);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get-order-list/{name}")
    public List<OrderListDTO> getOrderListByUserName(@PathVariable(value = "name") String name) {

        List<Order> orderList=orderRepository.findByUserid(userRepository.findByUsername(name).get(0));
       // Order order=orderList.get(orderList.size()-1);//самый последний заказ
        Order order=orderList.get(0);
        List<OrderList> orderLists=orderListRepository.findByOrderID(order);
        List<OrderListDTO> orderListDTOS=new ArrayList<>();
        for(OrderList orderList1: orderLists){
            OrderListDTO orderListDTO=new OrderListDTO();
            orderListDTO.setOrderID(orderList1.getOrderID().getId().toString());
            orderListDTO.setProductName(orderList1.getProductID().getName());
            orderListDTO.setCount(orderList1.getCount().toString());
            orderListDTOS.add(orderListDTO);
        }
        return orderListDTOS;

    }





    //Удаление по id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") Integer id ) {
        try {
            OrderList orderList=orderListRepository.getById(id);
            orderList.setProductID(null);
            orderListRepository.save(orderList);
            orderListRepository.delete( orderListRepository.getById(id));
            return (ResponseEntity.ok(HttpStatus.OK));
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.NOT_FOUND);
        }
    }


}
