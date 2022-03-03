package com.store.dto;

import com.store.model.Category;
import com.store.model.Product;

public class CategoryDTO {

    private String name;

    private String product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public CategoryDTO(Category category){
        this.name= category.getName();
        StringBuilder stringBuilder=new StringBuilder();
        int i=0;
        for(Product product: category.getProduct()){
            if(i!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append(product.getName());
            i++;
        }
        product=stringBuilder.toString();
    }

   public  CategoryDTO(){}
}
