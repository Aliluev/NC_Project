import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { environment } from "src/environments/environment";
import { TokenStorageService } from "../authorization/token-storage.service";
import { Bucket } from "../entities/bucket";

@Component({
  selector: 'bucket-component',
  templateUrl: "./bucket.component.html",
  styleUrls: ["./bucket.component.css"]
})
export class BucketComponent {

  constructor(private tokenStorage: TokenStorageService, private http: HttpClient) {
    this.getBucket();
  }

  user = "";
  bucket: Bucket[] = [];
  emptyBacket: Bucket[] = [];

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.user = <string>this.tokenStorage.getUsername();
    }
  }

  getBucket() {
    this.http.get<Bucket[]>(environment.backendUrl + '/order-list/get-order-list/' + this.tokenStorage.getUsername()).subscribe((data: Bucket[]) => this.bucket = data);
  }

  deleteProduct(orderListId: string) {
    this.http.delete(environment.backendUrl + '/order-list/delete/' + orderListId
    ).subscribe((data: any) => { console.log("ok") },
      (error: any) => console.log("eror"));
    this.bucket = this.bucket.filter(c => (c.orderListId !== orderListId));
  }

  ordered() {
    this.http.post(environment.backendUrl + '/status/status-ordered', this.tokenStorage.getUsername()).subscribe((data: any) => {
      this.bucket = this.emptyBacket;
      this.getBucket();
    },
      (error: any) => {
        this.bucket = this.emptyBacket;
        this.getBucket();
      });
    this.getBucket();
  }
}