import { Component } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { User } from "src/app/entities/user";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent {




    buttonDelete = false;
    buttonUpdate = false;
    user: User = new User("", "", "", "");
    id = "";
    users: User[] = [];
    templateUser: User = new User("", "", "", "");
    name = "";
    isEror = false;

    methodDeleteUser() {
        if (this.buttonDelete == false) {
            this.buttonDelete = true;
        } else {
            this.buttonDelete = false;
        }

    }

    methodUpdateUser() {
        if (this.buttonUpdate == false) {
            this.buttonUpdate = true;
        } else {
            this.buttonUpdate = false;
        }

    }


    getUsers() {
        return this.http.get<User[]>('http://localhost:8080/user/get-all');
    }

    loadUsers() {

        this.getUsers().subscribe((data: User[]) => this.users = data);
    }

    constructor(private http: HttpClient) {
        this.loadUsers();
    }

    updateUser(user: User) {

        this.http.put('http://localhost:8080/user/update', user).subscribe((data: any) => { console.log("ok") },
            (error: any) => console.log("eror"));
        let temUser = new User(user.username, user.phone, user.email, user.roles);
        this.users = this.users.filter(c => (c.username !== user.username));
        this.users.push(temUser);
    }

    errorMessage = "";


    deleteUser(name: string | string) {
        this.http.delete('http://localhost:8080/user/delete/' + name).subscribe((data: any) => {
            console.log("ok");
            this.users = this.users.filter(c => (c.username !== name));
        },
            (error: any) => {
                console.log("eror");
                this.isEror = true;
                this.errorMessage = error.error.message;
            });
    }


}