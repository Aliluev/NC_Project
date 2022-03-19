package com.store.model;

import com.store.dto.CategoryDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "categoryid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    private List<Product> product;

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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Category() {
    }

    public Category(CategoryDTO categoryDTO) {
        this.name = categoryDTO.getName();
    }

    public Category(String string) {
        this.name = string;
    }
}




