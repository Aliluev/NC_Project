package com.store.controller;
import com.store.model.Category;
import com.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @PostMapping("/category/create")
    public void createCategory(@RequestBody Category category){
        repository.save(category);
    }

    @DeleteMapping("/category/delete/{id}")
    public HashMap<String,Boolean> deleteCategory(@PathVariable(value = "id") Integer id ){

        Category category=repository.getById(id);
        repository.deleteById(id);

        HashMap<String,Boolean> responce=new HashMap<>();
        responce.put("Deleted",true);
        return responce;
    }

}
