import { Component, OnInit } from "@angular/core";
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../entities/user';
import { Role } from "../entities/role";
import { TokenStorageService } from "../authorization/token-storage.service";
import { environment } from "src/environments/environment";


@Component({
  selector: 'admin-role',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminRole implements OnInit {
  id = "";
  users = this.http.get<User[]>(environment.backendUrl + '/user/get-all');

  roles = this.http.get<Role[]>(environment.backendUrl + '/role/get-all-role');

  allowedToEnter = false;
  roleAdmin = false;
  roleSuperAdmin = false;
  rolesUser: Array<string> = [];
  perem: string = "";

  allowed() {
    for (var i = 0; i < this.rolesUser.length; i++) {
      console.log("Зашёл")
      if (this.rolesUser[i] == "ROLE_ADMIN" || this.rolesUser[i] == "ROLE_SUPERADMIN") {
        this.allowedToEnter = true;
      }
    }
    this.perem = this.rolesUser[0];
  }

  user: User = new User("", "", "", "");

  methodPostUser(user: User) {
    this.http.post(environment.backendUrl + '/user/create', user).subscribe((data: any) => { console.log("ok") },
      (error: any) => console.log("eror"));
  }

  methodDeleteUser(id: number | string) {
    this.http.delete(environment.backendUrl + '/user/delete/' + id
    ).subscribe((data: any) => { console.log("ok") },
      (error: any) => console.log("eror"));
  }

  methodUpdateUser(user: User | undefined) {
    this.http.put(environment.backendUrl + '/user/update', user).subscribe((data: any) => { console.log("ok") },
      (error: any) => console.log("eror"));
  }

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
    this.rolesUser = tokenStorage.getAuthorities();
    this.allowed();
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.rolesUser = this.tokenStorage.getAuthorities();
      this.perem = <string>this.tokenStorage.getUsername();
    }
  }

} 