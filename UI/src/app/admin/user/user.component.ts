import { Component } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { User } from "src/app/entities/user";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent{
    users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');
    constructor(private http: HttpClient){ }
}