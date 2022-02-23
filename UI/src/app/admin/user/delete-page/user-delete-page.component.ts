import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { User } from "src/app/entities/user";

@Component({
    selector:'user-delete-page',
    templateUrl:'./user-delete-page.component.html',
    styleUrls: ['./user-delete-page.component.css']
})
export class UserDeletePage{

    user: User =new User("","","","") ;
    id="";
    constructor(private http: HttpClient){ }

   
  methodDeleteUser(id: number | string){
    this.http.delete('http://localhost:8080/user/delete/'+id
    //{
    // params: new HttpParams().set('', id)}
    ).subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));
  }

}