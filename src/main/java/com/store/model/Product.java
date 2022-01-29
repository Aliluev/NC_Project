package com.store.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
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
    private List<Product> product;

}



