import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { User } from "src/app/entities/user";

@Component({
    selector:'user-add-page',
    templateUrl:'./user-add-page.component.html',
    styleUrls: ['./user-add-page.component.css']
})
export class UserAddPage{

    user: User =new User("","","","") ;

    constructor(private http: HttpClient){ }

    methodPostUser(user: User){
        this.http.post('http://localhost:8080/user/create',user).subscribe((data:any) => {console.log("ok")},
          (error: any)=> console.log("eror"));
      }

}