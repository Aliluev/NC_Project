import { Component, OnInit} from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {User} from '../entities/user';
import { Role } from '../entities/role';
import { HttpParams } from '@angular/common/http'

   
@Component({
    selector: 'my-appp',
    templateUrl: './user.component.html',
})
export class UserComponentTest implements OnInit { 
    user: User | undefined;
    user2: User=new User("","","","") ;
    roles: number=0 ;
    role: Role | undefined; 

   methodPost(user2: User,roles:number){
     //  user2.roles.push(roles);
    //   this.http.post('http://localhost:8080/user/create',user2).subscribe((data:any) => {console.log("ok")},
     //  (error: any)=> console.log("eror"));
    }

    constructor(private http: HttpClient){}

    
      
    ngOnInit(){
       // this.http.get('http://localhost:8080/user/1').subscribe((user => this.user=new User(data.id, data.username, data.password, data.phone, data.email));
      
       this.http.get('http://localhost:8080/user/9').subscribe((data:any) => this.user=new User(data.username, data.phone, data.email, data.roles));
       this.http.get('http://localhost:8080/role/1').subscribe((data:any) => this.role=new Role(data.name, data.users));

      



    }
}