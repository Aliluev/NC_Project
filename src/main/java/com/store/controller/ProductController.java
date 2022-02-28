package com.store.controller;
import com.store.dto.ProductDTO;
import com.store.model.Category;
import com.store.model.Product;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAllUserDTO(){
        List<Product> list= repository.findAll();
        List<ProductDTO> productDTOS=new ArrayList<>();

        for(Product product: list){
            productDTOS.add(new ProductDTO(product));
        }

        return ResponseEntity.ok(productDTOS);
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductDTO productDTO){
        List<Category> findCategory=categoryRepository.findByName(productDTO.getCategory());
        if((findCategory.size()==1)&&!(productDTO.equals(findCategory.get(0).getName()))){
            Product product=new Product(productDTO);
            product.setCategory(findCategory);
        }else {
            Category category = new Category(productDTO.getCategory());
            categoryRepository.save(category);
            List<Category> saveCategory = categoryRepository.findByName(productDTO.getCategory());
            Product product = new Product(productDTO);
            product.setCategory(saveCategory);
            repository.save(product);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") Integer id ) {
        repository.deleteById(id);
       return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO){
        // Product.category в моей концепции не может быть пустой
        //split
        String[] strings=productDTO.getCategory().split(",");
        List<Category> categories=new ArrayList<>();
        int i=0;
        for(String string:strings){
            List<Category> findCategory=categoryRepository.findByName(strings[i]);
            if((findCategory.size()==1)&&!(productDTO.equals(findCategory.get(0).getName()))){
                categories.add(findCategory.get(0));
            }else {
                Product product=new Product(productDTO);
                product.setCategory(strings[i]);
                repository.save(product);
                //вытащить id чтобы
                List<Category> findNewCategory=categoryRepository.findByName(strings[i]);
                categories.add(findNewCategory.get(0));
            }
            i++;
        }
        List<Product> findProduct=repository.findByName(productDTO.getName());
        Product product=findProduct.get(0);
        product.setPrice(Integer.parseInt(productDTO.getPrice()));
        product.setCategory(categories);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}