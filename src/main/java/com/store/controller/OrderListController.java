package com.store.controller;

import com.store.dto.AddProductDTO;
import com.store.dto.OrderListDTO;
import com.store.dto.ProductDTO;
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


    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

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
    public ResponseEntity.BodyBuilder addProductfromOrderList(@RequestBody AddProductDTO addProductDTO) {
        OrderList orderList = new OrderList();
        Product product = productRepository.findByName(addProductDTO.getProductName()).get(0);
        User user = userRepository.findByUsername(addProductDTO.getUserName()).get(0);
        //      List<Order> orderLists=orderRepository.getByUserid(user);


        // if(orderLists.size()==0){
        //     Order order=new Order();
        //    order.setStatusid(statusRepository.findByName("begin").get(0));//установим begin
        if (statusRepository.getByName("begin").size() == 0) {
            Status status = new Status();
            status.setName("begin");
            statusRepository.save(status);
            // order.setStatusid(statusRepository.getByName("begin").get(0));
        }

        //    order.setUserid(user);
        //    order.setDate(new Date().toString());
        //    orderRepository.save(order);
        //  }

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


        //    orderLists=orderRepository.getByUserid(user);
        //

        //  List<OrderList> orderListOsmotr=orderListRepository.findByProductID(product);
        List<OrderList> orderListOsmotr = orderListRepository.findByOrderIDAndProductID(order, product);
        if (orderListOsmotr.size() == 1) {
            OrderList orderList1 = orderListOsmotr.get(0);
            orderList1.setCount(orderList1.getCount() + Integer.parseInt(addProductDTO.getCount()));
            if (orderList1.getCount() > product.getCount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST);
            }
            orderListRepository.save(orderList1);
        } else {
            orderList.setOrderID(order);
            orderList.setProductID(product);
            orderList.setCount(Integer.parseInt(addProductDTO.getCount()));
            if (orderList.getCount() > product.getCount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST);
            }
            orderListRepository.save(orderList);
        }
        return ResponseEntity.status(HttpStatus.OK);
    }


    @GetMapping("/get-order-list/{name}")
    public List<OrderListDTO> getOrderListByUserName(@PathVariable(value = "name") String name) {
        User user=userRepository.getByUsername(name).get(0);
        Status status=statusRepository.getByName("begin").get(0);
        if(orderRepository.getByUseridAndStatusid(user,status).size()==0){
            Order order=new Order();
            order.setUserid(user);
            order.setStatusid(status);
            order.setDate(new Date().toString());
        }
       // List<Order> orderList=orderRepository.findByUserid(userRepository.findByUsername(name).get(0));
       // Order order=orderList.get(orderList.size()-1);//самый последний заказ
      //  Order order=orderList.get(0);
        Order order=orderRepository.getByUseridAndStatusid(user,status).get(0);
        List<OrderList> orderLists=orderListRepository.findByOrderID(order);
        List<OrderListDTO> orderListDTOS=new ArrayList<>();
        for(OrderList orderList1: orderLists){
            OrderListDTO orderListDTO=new OrderListDTO();
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

        Order order=orderRepository.getById(Integer.parseInt(id));
        List<OrderList> orderLists=orderListRepository.findByOrderID(order);
        List<OrderListDTO> orderListDTOS=new ArrayList<>();
        for(OrderList orderList1: orderLists){
            OrderListDTO orderListDTO=new OrderListDTO();
            orderListDTO.setOrderID(orderList1.getOrderID().getId().toString());
            orderListDTO.setOrderListId(orderList1.getId().toString());
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
            orderListRepository.delete(orderList);
          // orderList.setProductID(null);
           // orderListRepository.save(orderList);
         //   orderListRepository.delete( orderListRepository.getById(id));
            return (ResponseEntity.ok(HttpStatus.OK));
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.NOT_FOUND);
        }
    }


}
