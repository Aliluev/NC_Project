package com.store.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "storage")
@Data
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    Product product;

    private Integer count;

    private String address;
}
