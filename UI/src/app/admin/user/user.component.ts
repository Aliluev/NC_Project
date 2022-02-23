import { Component } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { User } from "src/app/entities/user";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent{

    canAddUser=false;
    butt="";
    methodCanAddUser(){
        if(this.canAddUser==false){
            this.canAddUser=true;
            this.butt="/add"
        }else{
            this.canAddUser=false;
            this.butt="/"
        }

    }


   

    users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');
    constructor(private http: HttpClient){ 
        this.methodCanAddUser();
    }
}