package com.store.repository;

import com.store.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDbRepository extends JpaRepository<Image,Integer> {

}
