import { Component, OnInit} from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {User} from '../entities/user';
import { Role } from '../entities/role';
   
@Component({
    selector: 'my-appp',
    templateUrl: './user.component.html',
})
export class UserComponent implements OnInit { 
    user: User | undefined;

    role: Role | undefined; 

    constructor(private http: HttpClient){}
      
    ngOnInit(){
       // this.http.get('http://localhost:8080/user/1').subscribe((user => this.user=new User(data.id, data.username, data.password, data.phone, data.email));
        
       this.http.get('http://localhost:8080/user/9').subscribe((data:any) => this.user=new User(data.username, data.phone, data.email, data.roles));
       this.http.get('http://localhost:8080/role/1').subscribe((data:any) => this.role=new Role(data.name, data.users));
    }
}