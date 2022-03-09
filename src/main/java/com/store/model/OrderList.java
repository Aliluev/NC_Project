package com.store.model;

import javax.persistence.*;

@Entity
@Table(name = "order_list")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_list_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orderid")
    private Order orderID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productid")
    private Product productID;

    @Column(name = "count")
    private Integer count;

    public OrderList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
