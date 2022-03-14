package com.store.dto;

public class AddProductDTO {

    private String userName;

    private String productName;

    private String count;

    public AddProductDTO(String userName, String productName, String count) {
        this.userName = userName;
        this.productName = productName;
        this.count = count;
    }

    public AddProductDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
