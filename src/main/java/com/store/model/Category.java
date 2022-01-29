package com.store.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name="product_category",
            joinColumns = @JoinColumn(name="categoryid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    private List<Product> product;
}




