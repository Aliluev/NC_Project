package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.model.*;
import com.store.repository.CategoryRepository;
import com.store.repository.ImageDbRepository;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ImageDbRepository imageDbRepository;

    @Value("${app.imageUrl}")
    private String imageUrl;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, ImageDbRepository imageDbRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageDbRepository = imageDbRepository;
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAllUserDTO() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : productList) {
            productDTOS.add(new ProductDTO(product));
        }

        return ResponseEntity.ok(productDTOS);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO) {

        try {
            Integer checkCount = Integer.parseInt(productDTO.getCount());
            Integer checkPrice = Integer.parseInt(productDTO.getPrice());
        } catch (RuntimeException exception) {
            return new Response().myResponseBadRequest(new MessageResponse("Wrong input count or price"));
        }

        if ((Integer.parseInt(productDTO.getCount()) == 0) || Integer.parseInt(productDTO.getPrice()) == 0) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Count or Price should be > 0"));
        }

        if (productRepository.existsByName(productDTO.getName())) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Name of Product is exist"));
        }

        if (productDTO.getImage().equals("false")) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Image not load"));
        }

        Product product = new Product(productDTO);
        List<Image> imageList = imageDbRepository.findAll();

        Image image = imageList.get(imageList.size() - 1);
        product.setImage(imageUrl + image.getId());

        String[] productCategoryList = productDTO.getCategory().split(",");
        List<Category> categoryList = new ArrayList<>();
        for (String string : productCategoryList) {
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
        return new Response().myResponseOK();
    }


    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable(value = "name") String name) {
        try {
            List<Product> productList = productRepository.findByName(name);
            productRepository.delete(productList.get(0));
            return new Response().myResponseOK();
        } catch (RuntimeException exception) {
            return new Response().myResponseNotFound(new MessageResponse("Error: Product Not Found"));
        }
    }


    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO) {

        // Price and count validation
        try {
            Integer checkInt = Integer.parseInt(productDTO.getCount());
            Integer checkPrice = Integer.parseInt(productDTO.getPrice());
        } catch (RuntimeException exception) {
            return new Response().myResponseBadRequest(new MessageResponse("Wrong input count or price"));
        }

        if (!productRepository.existsByName(productDTO.getName())) {
            return new Response().myResponseBadRequest(new MessageResponse("Error: Product is not exists"));
        }

        String[] productCategoryList = productDTO.getCategory().split(",");
        List<Category> productCategoryCollection = new ArrayList<>();

        int i = 0;

        for (String categoryName : productCategoryList) {
            List<Category> findCategory = categoryRepository.findByName(productCategoryList[i]);
            if (findCategory.size() != 1) {
                Category category = new Category(categoryName);
                categoryRepository.save(category);
                findCategory = categoryRepository.findByName(categoryName);
            }
            productCategoryCollection.add(findCategory.get(0));
            i++;

        }
        List<Product> findProduct = productRepository.findByName(productDTO.getName());

        String newImageUrl;
        if (productDTO.getImage().equals("true")) {
            List<Image> imageList = imageDbRepository.findAll();
            Image image = imageList.get(imageList.size() - 1);
            newImageUrl = imageUrl + image.getId();
        } else {
            newImageUrl = findProduct.get(0).getImage();
        }

        Product product;
        if (findProduct.size() > 0) {
            product = findProduct.get(0);
            product.setPrice(Integer.parseInt(productDTO.getPrice()));
            product.setCount(Integer.parseInt(productDTO.getCount()));
        } else {
            product = new Product(productDTO);
        }
        product.setImage(newImageUrl);
        product.setCategory(productCategoryCollection);
        productRepository.save(product);
        return new Response().myResponseOK();
    }

}
