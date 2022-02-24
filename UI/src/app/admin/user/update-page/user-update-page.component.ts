import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { User } from "src/app/entities/user";

@Component({
    selector:'user-update-page',
    templateUrl:'./user-update-page.component.html',
    styleUrls: ['./user-update-page.component.css']
})
export class UserUpdatePage{
    user: User =new User("","","","") ;
    id="";
    constructor(private http: HttpClient){ }

    users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');
   
    methodUpdateUser(id: number | string, user: User| undefined){
     
      this.http.put('http://localhost:8080/user/update-user/'
      +id,user).subscribe((data:any) => {console.log("ok")},
      (error: any)=> console.log("eror"));
    }

}