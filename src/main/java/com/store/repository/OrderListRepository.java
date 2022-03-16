package com.store.repository;

import com.store.model.Order;
import com.store.model.OrderList;
import com.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList,Integer> {

    List<OrderList> findByOrderID(Order order);

    List<OrderList> findByProductID(Product product);
    List<OrderList> findByOrderIDAndProductID(Order order, Product product);
}
