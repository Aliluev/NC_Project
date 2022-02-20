import { Component } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { User } from '../entities/user';
import { Role } from "../entities/role";


@Component({
    selector: 'admin-role',
    templateUrl: './admin.component.html',
})

export class AdminRole {
  
  users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');
  
  roles= this.http.get<Role[]>('http://localhost:8080/role/get-all-role');
  
 constructor(private http: HttpClient){ }

} 