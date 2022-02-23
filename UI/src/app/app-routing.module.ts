import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleComponent } from './admin/role/role.component';
import { UserComponent } from './admin/user/user.component';

const routes: Routes  = [
  {path:'', component: UserComponent },
  {path:'role', component:RoleComponent}
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
