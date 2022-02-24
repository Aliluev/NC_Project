import { Component } from "@angular/core";
import {HttpClient, HttpParams} from '@angular/common/http';
import { User } from '../entities/user';
import { Role } from "../entities/role";


@Component({
    selector: 'admin-role',
    templateUrl: './admin.component.html',
    styleUrls:['./admin.component.css']
})

export class AdminRole {
  id="";
  users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');
  
  roles= this.http.get<Role[]>('http://localhost:8080/role/get-all-role');
  

  //Методы post 
  user: User =new User("","","","") ;

  methodPostUser(user: User){
    this.http.post('http://localhost:8080/user/create',user).subscribe((data:any) => {console.log("ok")},
      (error: any)=> console.log("eror"));
  }

  methodDeleteUser(id: number | string){
    this.http.delete('http://localhost:8080/user/delete/'+id
    //{
    // params: new HttpParams().set('', id)}
    ).subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));
  }

  methodUpdateUser(id: number | string, user: User| undefined){
    this.http.put('http://localhost:8080/user/update-user/'+id,user).subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));
  }
  
 constructor(private http: HttpClient){ }

} 