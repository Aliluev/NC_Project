package com.store.dto;

import com.store.model.Category;
import com.store.model.Product;

public class ProductDTO {

    private String name;

    private String price;

    private String category;

    private String count;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    /*
    private String storage;

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

     */

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductDTO(Product product){
        this.name= product.getName();
        this.price=product.getPrice().toString();
        if(product.getCount()==null){
            this.count="0";
        }else {
            this.count = product.getCount().toString();
        }
        this.image=product.getImage();

        StringBuilder stringBuilder=new StringBuilder();
        int i=0;
        for(Category category: product.getCategory()){
           if(i!=0) {
               stringBuilder.append(",");
           }
            stringBuilder.append(category.getName());
           // stringBuilder.append((category.getId()));
           i++;
        }
        category=stringBuilder.toString();

        /* Добавление магазина
        StringBuilder storageString=new StringBuilder();
        i=0;
        for(Storage storage:product.getStorage()){
            if(i!=0){
                storageString.append(",");
            }
            storageString.append(storage.getAddress());
        }
        storage=storageString.toString();

         */
    }

    public ProductDTO(){}

}
