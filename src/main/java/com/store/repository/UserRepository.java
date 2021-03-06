package com.store.repository;

import com.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String string);

    List<User> getByUsername(String string);

    Boolean existsByUsername(String name);


}
