import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './admin/categories/categories.component';
import { ProductComponent } from './admin/product/product.component';
import { RoleComponent } from './admin/role/role.component';
import { UserComponent } from './admin/user/user.component';

const routes: Routes  = [
  {path:'', component: UserComponent},
  {path:'role', component:RoleComponent},
  {path:'product', component:ProductComponent},
  {path:'category', component:CategoriesComponent}
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
