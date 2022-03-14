package com.store.repository;

import com.store.model.Order;
import com.store.model.Product;
import com.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
   // Order findByUserid(Integer id);
    List<Order> findByUserid(User user);

}
