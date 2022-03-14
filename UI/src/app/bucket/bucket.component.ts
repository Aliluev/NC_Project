
import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { TokenStorageService } from "../authorization/token-storage.service";
import { Bucket } from "../entities/bucket";




@Component({
    selector: 'bucket-component',
    templateUrl: "./bucket.component.html",
    styleUrls: ["./bucket.component.css"]
})
export class BucketComponent{

    constructor( private tokenStorage: TokenStorageService, private http: HttpClient) { 
    this.getBucket();  
    }
     
  user="";
  bucket:Bucket[]=[];
  
      ngOnInit() {
        if (this.tokenStorage.getToken()) {
          this.user = <string>this.tokenStorage.getUsername();
        }
      }

      getBucket(){
        this.http.get<Bucket[]>('http://localhost:8080/order-list/get-order-list/'+this.tokenStorage.getUsername()).subscribe((data: Bucket[])=>this.bucket=data); 
      }

  
}