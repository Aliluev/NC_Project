import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { TokenStorageService } from "../authorization/token-storage.service";
import { OrderList } from "../entities/orderList";
import { Product } from "../entities/product";



@Component({
    selector: 'market-component',
    templateUrl: "./market.component.html",
    styleUrls: ["./market.component.css"]
})
export class MarketComponent{

    clickBucket=false;
    addBucket(){
        if(this.clickBucket==false){
            this.clickBucket=true;
        }else{
            this.clickBucket=false;
        }
    }

   
   count=new Array();
    
    imageIdList: number[]=[];
    orderList:OrderList=new OrderList("","","");
    onSubmit(products2:Product,count:string, idx:number){
        this.orderList.userName=this.userName;
        this.orderList.productName=products2.name;
        this.orderList.count=count;
        this.http.post('http://localhost:8080/order-list/add-product',this.orderList).subscribe((data:any) => {console.log("ok");
        this.count=new Array();},
        (error: any)=> {
            console.log("eror"); 
            this.count=new Array();
       this.count[idx]="Wrong input count";});

    }


    getCountImages(){
        return this.http.get<number[]>('http://localhost:8080/image/get-all-count-products');
 
      }

      loadCategories(){
       this.getCountImages().subscribe((data: number[])=>{
        for(let i=0; i<data.length;i++){
            this.stringIdList.push(this.num+data[i]);
            this.imageIdList=data;
        }});
        this.numb=this.imageIdList.length;
       for(let i=0; i<this.imageIdList.length;i++){
           this.stringIdList.push(this.num+this.imageIdList[i]);
       }

    }

    constructor(private http: HttpClient,private tokenStorage: TokenStorageService){
        this.loadCategories();
        this.loadProducts();
     }

     userName: string="";

     ngOnInit() {
        if (this.tokenStorage.getToken()) {
          this.userName = <string>this.tokenStorage.getUsername();
        }
      }
  
    	
     stringIdList: string[]=[];
    num:string="http://localhost:8080/image/";
    
    numb=0;


    products2:Product[]=[];
    templateProduct:Product=new Product("","","","","");

    getProducts(){
      return this.http.get<Product[]>('http://localhost:8080/product/get-all');
    }

    loadProducts(){
        
        this.getProducts().subscribe((data: Product[])=>this.products2=data); 
    }




    
}