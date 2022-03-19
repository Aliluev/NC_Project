package com.store.repository;

import com.store.model.Status;
import com.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    List<Status> findByName(String string);

    List<Status> getByName(String string);
}
