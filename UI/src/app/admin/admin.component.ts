import { Component, OnInit } from "@angular/core";
import {HttpClient, HttpParams} from '@angular/common/http';
import { User } from '../entities/user';
import { Role } from "../entities/role";
import { TokenStorageService } from "../authorization/token-storage.service";


@Component({
    selector: 'admin-role',
    templateUrl: './admin.component.html',
    styleUrls:['./admin.component.css']
})

export class AdminRole implements OnInit{
  id="";
  users= this.http.get<User[]>('http://localhost:8080/user/get-all');
  
  roles= this.http.get<Role[]>('http://localhost:8080/role/get-all-role');

  allowedToEnter=false;
  roleAdmin=false;
  roleSuperAdmin=false;
  rolesUser:Array<string> = [];
  perem:string="";

  allowed(){
    for(var i=0;i<this.rolesUser.length;i++){
      console.log("Зашёл")
      if(this.rolesUser[i]=="ROLE_ADMIN"||this.rolesUser[i]=="ROLE_SUPERADMIN"){
        this.allowedToEnter=true;
      }
    }
    this.perem=this.rolesUser[0];
  }
 // private roles: Array<string> = [];
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

  methodUpdateUser(user: User| undefined){
    this.http.put('http://localhost:8080/user/update',user).subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));
  }
  
 constructor(private http: HttpClient,private tokenStorage: TokenStorageService){ 
   this.rolesUser=tokenStorage.getAuthorities();
   this.allowed();
 }

 ngOnInit() {
  if (this.tokenStorage.getToken()) {
    this.rolesUser = this.tokenStorage.getAuthorities();
    this.perem=<string>this.tokenStorage.getUsername();
  }
}

} 