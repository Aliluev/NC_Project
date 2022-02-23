import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleComponent } from './admin/role/role.component';
import { UserAddPage } from './admin/user/add-page/user-add-page.component';
import { UserDeletePage } from './admin/user/delete-page/user-delete-page.component';
import { UserUpdatePage } from './admin/user/update-page/user-update-page.component';
import { UserComponent } from './admin/user/user.component';

const routes: Routes  = [
  {path:'', component: UserComponent, children:[ 
    {path:'add', component: UserAddPage } ,{path:'delete',component:UserDeletePage},
    {path:'update', component:UserUpdatePage}
  ] },
  {path:'add/', redirectTo:''},
  {path:'delete/', redirectTo:''},
  {path:'role', component:RoleComponent}
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
