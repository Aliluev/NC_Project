package com.store.dto;

public class OrderListDTO {

    private Integer orderID;

    private Integer productID;

    private Integer count;

    public OrderListDTO() {
    }

    public OrderListDTO(Integer orderID, Integer productID, Integer count) {
        this.orderID = orderID;
        this.productID = productID;
        this.count = count;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }




}
