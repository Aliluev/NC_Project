import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminRole } from './admin/admin.component';
import { CategoriesComponent } from './admin/categories/categories.component';
import { ProductComponent } from './admin/product/product.component';
import { RoleComponent } from './admin/role/role.component';
import { UserComponent } from './admin/user/user.component';
import { AuthorizationComponent } from './authorization/authorization.component';
import { MarketComponent } from './market/market.component';
import { PageComponent } from './page/page.component';
import { RegistrationComponent } from './registration/registration.component';
import { BucketComponent } from './bucket/bucket.component';
import { OrdersComponent } from './admin/orders/orders.component';

const routes: Routes  = [
  {path:'admin', component: AdminRole ,
children:[
  {path:'user', component:UserComponent},
  {path:'role', component:RoleComponent},
  {path:'product', component:ProductComponent},
  {path:'category', component:CategoriesComponent},
  {path:'orders',component:OrdersComponent},
  {path:'market', component:MarketComponent}
]
},
{path:'', component:AuthorizationComponent},

  {path:'bucket', component:BucketComponent},


  {path:'market', component:MarketComponent},
  {path:"auth", component:AuthorizationComponent},
  {path:"registration", component:RegistrationComponent},
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
