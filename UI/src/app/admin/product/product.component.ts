import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Product } from "src/app/entities/product";

@Component({
    selector: 'product-component',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css']
})
export class ProductComponent{

    buttonAdd=false;
    buttonDelete=false;
    buttonUpdate=false;


    methodDeleteProduct(){
        if(this.buttonDelete==false){
            this.buttonDelete=true;
        }else{
            this.buttonDelete=false;
        }
    }

    methodUpdateProduct(){
        if(this.buttonUpdate==false){
            this.buttonUpdate=true;
        }else{
            this.buttonUpdate=false;
        }
    }

    methodCanAddProduct(){
        if(this.buttonAdd==false){
            this.buttonAdd=true;
        }else {
            this.buttonAdd=false;
        }
        
    }

    products2:Product[]=[];
    templateProduct:Product=new Product("",0,"",0,"");

    getProducts(){
      return this.http.get<Product[]>('http://localhost:8080/product/get-all');
    }

    loadProducts(){
        
        this.getProducts().subscribe((data: Product[])=>this.products2=data); 
    }


    products=this.http.get<Product[]>('http://localhost:8080/product/get-all');
    product:Product=new Product("",0,"",0,"");
    name="";


    deleteProduct(name: string | string){
        this.http.delete('http://localhost:8080/product/delete/'+name).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
   // this.http.get<Product[]>('http://localhost:8080/product/get-all').subscribe((data: Product[])=>this.products2=data);  
    this.products2=this.products2.filter(c =>(c.name !== name));

   // this.products2=this.products2.splice(1,1);
}

      postProduct(product: Product){
        this.http.post('http://localhost:8080/product/create',product).subscribe((data:any) => {console.log("ok")},
          (error: any)=> console.log("eror"));
          //this.loadProducts();
          let temProduct=new Product(product.name,product.price,product.category,product.count,product.image);
          this.products2.push(temProduct);
      }

      
    updateProduct(product: Product){
     
        this.http.put('http://localhost:8080/product/update'
        ,product).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
        let temProduct=new Product(product.name,product.price,product.category,product.count,product.image);
        this.products2=this.products2.filter(c =>(c.name !== product.name));
        this.products2.push(temProduct);
      }


    constructor(private http: HttpClient){ 
        this.loadProducts();
    }

}