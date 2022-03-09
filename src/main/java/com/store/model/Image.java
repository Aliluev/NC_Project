package com.store.model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    //@Lob-это то, как мы сообщаем JPA
    // о нашем намерении хранить
    // потенциально большой двоичный файл
   // @Lob
    private byte[] content;

    private String name;

    public Image() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
