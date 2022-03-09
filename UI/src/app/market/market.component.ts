import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Product } from "../entities/product";



@Component({
    selector: 'market-component',
    templateUrl: "./market.component.html",
    styleUrls: ["./market.component.css"]
})
export class MarketComponent{

    //imageIdList: number[] | undefined;
    imageIdList: number[]=[];
/*
    imageList= this.http.get('http://localhost:8080/image/save').subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));

    constructor(private http: HttpClient){ 
    this.imageList= this.http.get('http://localhost:8080/image/save').subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));
    }
    */

    getCountImages(){
        return this.http.get<number[]>('http://localhost:8080/image/get-all-count-products');
      }

      loadCategories(){
        
       // this.getCountImages().subscribe((data: number[])=>this.imageIdList=data); 
       this.getCountImages().subscribe((data: number[])=>{
        for(let i=0; i<data.length;i++){
            this.stringIdList.push(this.num+data[i]);
            this.imageIdList=data;
        }});
       // this.imageIdList=data}); 
      /*  this.imageIdList.forEach(function (value) {
            this.num
        })
        */
        this.numb=this.imageIdList.length;
       for(let i=0; i<this.imageIdList.length;i++){
           this.stringIdList.push(this.num+this.imageIdList[i]);
       }

    }

    constructor(private http: HttpClient){
        this.loadCategories();
        this.loadProducts();
     }
    	
     stringIdList: string[]=[];
    num:string="http://localhost:8080/image/";
    
    numb=0;


    products2:Product[]=[];
    templateProduct:Product=new Product("",0,"",0,"");

    getProducts(){
      return this.http.get<Product[]>('http://localhost:8080/product/get-all');
    }

    loadProducts(){
        
        this.getProducts().subscribe((data: Product[])=>this.products2=data); 
    }




    
}