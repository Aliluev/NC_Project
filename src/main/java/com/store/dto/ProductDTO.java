package com.store.dto;

import com.store.model.Category;
import com.store.model.Product;

public class ProductDTO {

    private String name;

    private String price;

    private String category;

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
    }

    public ProductDTO(){}

}
