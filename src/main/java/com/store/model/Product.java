package com.store.model;




import com.store.dto.ProductDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer price;

    @ManyToMany
    @JoinTable(name="product_category",
            joinColumns = @JoinColumn(name="productid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<Category> category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public void setCategory(String string){
        Category category=new Category(string);
        category.setName(string);
        this.category=new ArrayList<>();
        this.category.add(category);
    }

    public Product(){}

    public Product(ProductDTO productDTO){
        this.name=productDTO.getName();
        this.price=Integer.parseInt(productDTO.getPrice());
    }


}



