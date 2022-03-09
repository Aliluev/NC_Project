package com.store.model;
import com.store.dto.StatusDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Status() {
    }

    public Status(StatusDTO statusDTO) {
        this.name = statusDTO.getName();
    }

    @OneToMany(mappedBy = "statusid")
    private List<Order> order;

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

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
