
import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Registration } from "../entities/registration";



@Component({
    selector: 'registration-component',
    templateUrl: "./registration.component.html",
    styleUrls: ["./registration.component.css"]
})
export class RegistrationComponent{

    submitStatus=false;
    registration=new Registration("","","");

    onSubmit(registration:Registration) {
        this.http.post('http://localhost:8080/api/auth/signup',registration).subscribe((data:any) => {this.submitStatus=true},
        (error: any)=> console.log("eror"));
    }

    constructor(private http: HttpClient){ }


}