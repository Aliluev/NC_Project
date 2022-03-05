package com.store.dto;

public class OrderDTO {

    private String userID;

    private String statusID;

    private String date;

    public OrderDTO() {
    }

    public OrderDTO(String userID, String statusID, String data) {
        this.userID = userID;
        this.statusID = statusID;
        this.date = data;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
