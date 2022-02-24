package com.store.controller;
import com.store.model.Product;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping("/product/create")
    public void createProduct(@RequestBody Product product){
        repository.save(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public HashMap<String,Boolean> deleteProduct(@PathVariable(value = "id") Integer id ) {

        Product product=repository.getById(id);
        repository.deleteById(id);

        HashMap<String,Boolean> responce=new HashMap<>();
        responce.put("Deleted",true);
        return responce;
    }


}
