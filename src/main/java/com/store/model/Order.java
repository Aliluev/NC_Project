package com.store.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderss")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusid")
    private Status statusid;

    @Column(name = "order_date")
    String date;
    //Date date;

    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_list",
            joinColumns = @JoinColumn(name = "orderid"),
            inverseJoinColumns = @JoinColumn(name = "productid")
    )

     */



    @OneToMany(mappedBy = "orderID",cascade = CascadeType.ALL)
    private List<OrderList> orderLists;


    public Order() {
    }


    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Status getStatusid() {
        return statusid;
    }

    public void setStatusid(Status statusid) {
        this.statusid = statusid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

     */
}



