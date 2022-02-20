import { Component } from "@angular/core";
import { UserService } from "./user.service";
import {HttpClient} from '@angular/common/http';
import { User } from '../entities/user';


@Component({
    selector: 'admin-role',
    templateUrl: './admin.component.html',
})

export class AdminRole {
  //userRole: UserRole | undefined;
  
  users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');

 // constructor(public userService: UserService){}

 constructor(private http: HttpClient){ }

  //ngOnInit(){
 //   this.http.get('http://localhost:8080/role/1').subscribe((data:any) => this.role=new Role(data.name, data.users));
 // }

} 