import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleComponent } from './admin/role/role.component';
import { UserAddPage } from './admin/user/add-page/user-add-page.component';
import { UserComponent } from './admin/user/user.component';

const routes: Routes  = [
  {path:'', component: UserComponent, children:[ 
    {path:'add', component: UserAddPage }  
  ] },
  {path:'add/', redirectTo:'role'},
  {path:'role', component:RoleComponent}
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
