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

    private Integer count;

    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @ManyToMany
    @JoinTable(name="product_category",
            joinColumns = @JoinColumn(name="productid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<Category> category;

    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_list",
            joinColumns = @JoinColumn(name = "productid"),
            inverseJoinColumns = @JoinColumn(name = "orderid")
    )

     */
    @OneToMany(mappedBy = "productID",cascade = CascadeType.ALL)
    private List<OrderList> orderList;

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }


    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Storage> storage;

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }


     */


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
        this.count=Integer.parseInt(productDTO.getCount());
        this.image=productDTO.getImage();
    }


}



