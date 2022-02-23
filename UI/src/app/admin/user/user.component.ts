import { Component } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { User } from "src/app/entities/user";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent{

 

    buttonAdd="/add";
    buttonDelete="/delete";
    buttonUpdate="/update";

    methodCanAddUser(){
        if(this.buttonAdd=="/add"){
            this.buttonAdd="/";
            this.buttonDelete="/delete";
            this.buttonUpdate="/update";
        }else{
            this.buttonAdd="/add";
            this.buttonDelete="/delete";
            this.buttonUpdate="/update";
        }
        
    }

    methodDeleteUser(){
        if(this.buttonDelete=="/delete"){
            this.buttonDelete="/";
            this.buttonAdd="/add";
            this.buttonUpdate="/update";
        }else{
            this.buttonDelete="/delete";
            this.buttonAdd="/add";
            this.buttonUpdate="/update";
        }
    }

    methodUpdateUser(){
        if(this.buttonUpdate=="/update"){
            this.buttonUpdate="/";
            this.buttonAdd="/add";
            this.buttonDelete="/delete";
        }else{
            this.buttonUpdate="/update";
            this.buttonAdd="/add";
            this.buttonDelete="/delete";
        }
    }

   

    users= this.http.get<User[]>('http://localhost:8080/user-role/get-all-user-role');
    constructor(private http: HttpClient){ 
    }
}