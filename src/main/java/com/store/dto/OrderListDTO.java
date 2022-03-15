package com.store.dto;

public class OrderListDTO {

    private String orderID;

    private String orderListId;

    private String productName;

    private String count;


    public OrderListDTO() {
    }

    public OrderListDTO(String orderID, String productName, String count, String orderListId) {
        this.orderID = orderID;
        this.productName = productName;
        this.count = count;
        this.orderListId=orderListId;
    }

    public String getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(String orderListId) {
        this.orderListId = orderListId;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
