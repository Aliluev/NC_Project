import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Role } from "src/app/entities/role";
import { environment } from "src/environments/environment";

@Component({
    selector: 'role-component',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.css']
})
export class RoleComponent {

    buttonAdd = false;
    buttonDelete = false;

    roles: Role[] = [];
    templateRole: Role = new Role(0, "");
    role: Role = new Role(0, "");
    name = "";
    erors = false;
    errorMessage = "";
    erorDelete = false;

    getRoles() {
        return this.http.get<Role[]>(environment.backendUrl + '/role/get-all-role');
    }

    loadRoles() {

        this.getRoles().subscribe((data: Role[]) => this.roles = data);
    }



    methodCanAddRole() {
        if (this.buttonAdd == false) {
            this.buttonAdd = true;
        } else {
            this.buttonAdd = false;
        }
    }

    methodDeleteRole() {
        if (this.buttonDelete == false) {
            this.buttonDelete = true;
        } else {
            this.buttonDelete = false;
        }
    }


    deleteRole(name: string | string) {
        this.http.delete(environment.backendUrl + '/role/delete/' + name).subscribe((data: any) => { console.log("ok") },
            (error: any) => {
                console.log("eror");
                this.erorDelete = true;
                this.errorMessage = error.error.message;

            });
        this.roles = this.roles.filter(c => (c.name !== name));
    }

    postRole(role: Role) {
        this.http.post(environment.backendUrl + '/role/create', role).subscribe((data: any) => {
            console.log("ok");
            let temRole = new Role(role.id, role.name);
            this.roles.push(temRole);
        },
            (error: any) => {
                console.log("eror");
                this.errorMessage = error.error.message;
                this.erors = true;
            });

    }


    constructor(private http: HttpClient) { this.loadRoles(); };
}
