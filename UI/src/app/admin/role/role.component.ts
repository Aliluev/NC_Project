import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Role } from "src/app/entities/role";

@Component({
    selector: 'role-component',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.css']
})
export class RoleComponent{
    roles= this.http.get<Role[]>('http://localhost:8080/role/get-all-role');

    constructor(private http: HttpClient){ }

}