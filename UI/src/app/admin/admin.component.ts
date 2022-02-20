import { Component } from "@angular/core";
import { UserRole } from "../entities/userRole";

@Component({
    selector: 'admin-role',
    templateUrl: './admin.component.html',
})

export class AdminRole{
  userRole: UserRole | undefined;

}