import { Component } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { User } from "src/app/entities/user";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent{

 

    //buttonAdd="/add";
    buttonAdd=false;
    //buttonDelete="/delete";
    buttonDelete=false;
    //buttonUpdate="/update";
    buttonUpdate=false;


    user: User =new User("","","","") ;
    id="";


    methodCanAddUser(){
        if(this.buttonAdd==false){
            this.buttonAdd=true;
        }else {
            this.buttonAdd=false;
        }
       
        /*
        if(this.buttonAdd=="/add"){
            this.buttonAdd="/";
            this.buttonDelete="/delete";
            this.buttonUpdate="/update";
        }else{
            this.buttonAdd="/add";
            this.buttonDelete="/delete";
            this.buttonUpdate="/update";
        }
        */
        
    }

    methodDeleteUser(){
        if(this.buttonDelete==false){
            this.buttonDelete=true;
        }else{
            this.buttonDelete=false;
        }
        
        /*
        if(this.buttonDelete=="/delete"){
            this.buttonDelete="/";
            this.buttonAdd="/add";
            this.buttonUpdate="/update";
        }else{
            this.buttonDelete="/delete";
            this.buttonAdd="/add";
            this.buttonUpdate="/update";
        }
        */
    }

    methodUpdateUser(){
        if(this.buttonUpdate==false){
            this.buttonUpdate=true;
        }else{
            this.buttonUpdate=false;
        }
        /*
        if(this.buttonUpdate=="/update"){
            this.buttonUpdate="/";
            this.buttonAdd="/add";
            this.buttonDelete="/delete";
        }else{
            this.buttonUpdate="/update";
            this.buttonAdd="/add";
            this.buttonDelete="/delete";
        }
        */
    }

   

    users= this.http.get<User[]>('http://localhost:8080/user/get-all');
    
    constructor(private http: HttpClient){ 
    }

    updateUser(id: number | string, user: User| undefined){
     
        this.http.put('http://localhost:8080/user/update-user/'
        +id,user).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
      }

      postUser(user: User){
        this.http.post('http://localhost:8080/user/create',user).subscribe((data:any) => {console.log("ok")},
          (error: any)=> console.log("eror"));
      }

         
  deleteUser(id: number | string){
    this.http.delete('http://localhost:8080/user/delete/'+id
    //{
    // params: new HttpParams().set('', id)}
    ).subscribe((data:any) => {console.log("ok")},
    (error: any)=> console.log("eror"));
  }

  
}