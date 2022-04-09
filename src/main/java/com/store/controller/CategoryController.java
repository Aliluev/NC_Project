package com.store.controller;

import com.store.dto.CategoryDTO;
import com.store.model.Category;
import com.store.model.MessageResponse;
import com.store.model.Response;
import com.store.repository.CategoryRepository;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDTO>> getAllUserDTO() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryDTOList.add(new CategoryDTO(category));
        }

        return ResponseEntity.ok(categoryDTOList);
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Category is exists"));
        }
        Category category = new Category(categoryDTO);
        categoryRepository.save(category);
        return new Response().myResponseOK();
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteCategory(@PathVariable(value = "name") String name) {
        try {
            List<Category> categories = categoryRepository.findByName(name);
            categoryRepository.delete(categories.get(0));
            return new Response().myResponseOK();
        } catch (RuntimeException exception) {
            return new Response().myResponseNotFound(new MessageResponse("Error: Category not found"));
        }
    }

}
