package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.model.Category;
import com.store.model.Image;
import com.store.model.MessageResponse;
import com.store.model.Product;
import com.store.repository.CategoryRepository;
import com.store.repository.ImageDbRepository;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {


    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ImageDbRepository imageDbRepository;

    @Value("${app.imageUrl}")
    private String imageUrl;

    @Autowired
    public ProductController(ProductRepository repository, CategoryRepository categoryRepository, ImageDbRepository imageDbRepository) {
        this.productRepository = repository;
        this.categoryRepository = categoryRepository;
        this.imageDbRepository = imageDbRepository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAllUserDTO() {
        List<Product> list = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : list) {
            productDTOS.add(new ProductDTO(product));
        }

        return ResponseEntity.ok(productDTOS);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO) {

        try {
            Integer checkInt = Integer.parseInt(productDTO.getCount());
            checkInt = Integer.parseInt(productDTO.getPrice());
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Wrong input count or price", HttpStatus.BAD_REQUEST);
        }

        if ((Integer.parseInt(productDTO.getCount()) == 0) || Integer.parseInt(productDTO.getPrice()) == 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Count or Price should be > 0"));
        }

        if (productRepository.existsByName(productDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name of Product is exist"));
        }

        if (productDTO.getImage().equals("false")) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Image not load"));
        }

        Product product = new Product(productDTO);
        List<Image> imageList = imageDbRepository.findAll();

        Image image = imageList.get(imageList.size() - 1);
        product.setImage(imageUrl + image.getId());

        String[] strings = productDTO.getCategory().split(",");
        List<Category> categoryList = new ArrayList<>();
        for (String string : strings) {
            List<Category> findCategory = categoryRepository.findByName(string);
            if (findCategory.size() == 1) {
                categoryList.add(findCategory.get(0));
            } else {
                Category category = new Category(productDTO.getCategory());
                categoryRepository.save(category);
                List<Category> saveCategory = categoryRepository.findByName(productDTO.getCategory());
                categoryList.add(saveCategory.get(0));
            }
        }

        product.setCategory(categoryList);
        productRepository.save(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable(value = "name") String name) {
        try {
            List<Product> productList = productRepository.findByName(name);
            productRepository.delete(productList.get(0));
            return (ResponseEntity.ok(HttpStatus.OK));
        } catch (RuntimeException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Product Not Found"));
        }
    }


    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO) {


        try {
            Integer checkInt = Integer.parseInt(productDTO.getCount());
            checkInt = Integer.parseInt(productDTO.getPrice());
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Wrong input count or price", HttpStatus.BAD_REQUEST);
        }

        if (!productRepository.existsByName(productDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Product is not exists"));
        }

        String[] strings = productDTO.getCategory().split(",");
        List<Category> categories = new ArrayList<>();

        int i = 0;

        for (String string : strings) {
            List<Category> findCategory = categoryRepository.findByName(strings[i]);
            if (findCategory.size() == 1) {
                categories.add(findCategory.get(0));
                i++;
            } else {
                Category category = new Category(string);
                categoryRepository.save(category);
                List<Category> findNewCategory = categoryRepository.findByName(string);
                categories.add(findNewCategory.get(0));
                i++;
            }

        }
        List<Product> findProduct = productRepository.findByName(productDTO.getName());

        String newImageUrl;
        if (productDTO.getImage().equals("true")) {
            List<Image> imageList = imageDbRepository.findAll();
            Image image = imageList.get(imageList.size() - 1);
            newImageUrl = imageUrl + image.getId();
        } else {
            newImageUrl = productDTO.getImage();
        }


        if (findProduct.size() > 0) {
            Product product = findProduct.get(0);
            product.setPrice(Integer.parseInt(productDTO.getPrice()));
            product.setCount(Integer.parseInt(productDTO.getCount()));
            product.setCategory(categories);
            product.setImage(newImageUrl);
            productRepository.save(product);
        } else {
            Product product = new Product(productDTO);
            product.setImage(newImageUrl);
            product.setCategory(categories);
            productRepository.save(product);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
