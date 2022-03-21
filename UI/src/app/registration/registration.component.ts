import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { environment } from "src/environments/environment";
import { Registration } from "../entities/registration";


@Component({
    selector: 'registration-component',
    templateUrl: "./registration.component.html",
    styleUrls: ["./registration.component.css"]
})
export class RegistrationComponent {

    submitStatus = false;
    registration = new Registration("", "", "", "");
    isLoginFailed = false;
    errorMessage = "";

    onSubmit(registration: Registration) {
        this.http.post(environment.backendUrl + '/api/auth/signup', registration).subscribe((data: any) => { this.submitStatus = true },
            (error: any) => {
                console.log("eror");
                this.errorMessage = error.error.message;
                this.isLoginFailed = true;
            });
    }

    constructor(private http: HttpClient) { }

}