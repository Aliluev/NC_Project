import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './admin/categories/categories.component';
import { ProductComponent } from './admin/product/product.component';
import { RoleComponent } from './admin/role/role.component';
import { UserComponent } from './admin/user/user.component';
import { AuthorizationComponent } from './authorization/authorization.component';
import { MarketComponent } from './market/market.component';
import { PageComponent } from './page/page.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes  = [
  {path:'', component: UserComponent},
  {path:'role', component:RoleComponent},
  {path:'product', component:ProductComponent},
  {path:'category', component:CategoriesComponent},
  {path:'market', component:MarketComponent},
  {path:"auth", component:AuthorizationComponent},
  {path:"page", component:PageComponent},
  {path:"registration", component:RegistrationComponent}
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
