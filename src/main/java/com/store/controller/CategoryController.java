package com.store.controller;
import com.store.dto.CategoryDTO;
import com.store.model.Category;
import com.store.repository.CategoryRepository;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDTO>> getAllUserDTO(){
        List<Category> list= categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS=new ArrayList<>();

        for(Category category: list){
            categoryDTOS.add(new CategoryDTO(category));
        }

        return ResponseEntity.ok(categoryDTOS);
    }


    @PostMapping("/create")
    public void createCategory(@RequestBody CategoryDTO categoryDTO){

        Category category=new Category(categoryDTO);
        categoryRepository.save(category);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteCategory(@PathVariable(value = "name") String name ){
        List<Category> categories =categoryRepository.findByName(name);
        categoryRepository.delete(categories.get(0));
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
