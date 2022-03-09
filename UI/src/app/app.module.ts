import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule }   from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { AdminRole } from './admin/admin.component';
import { RoleComponent } from './admin/role/role.component';
import { UserComponent } from './admin/user/user.component';
import { ProductComponent } from './admin/product/product.component';
import { CategoriesComponent } from './admin/categories/categories.component';
import { MarketComponent } from './market/market.component';



@NgModule({
  declarations: [
    AppComponent,
    AdminRole,
    RoleComponent,
    UserComponent,
    ProductComponent,
    CategoriesComponent,
    MarketComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
