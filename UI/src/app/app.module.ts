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
import { AuthorizationComponent } from './authorization/authorization.component';
import { httpInterceptorProviders } from './authorization/auth-interceptor';
import { PageComponent } from './page/page.component';
import { RegistrationComponent } from './registration/registration.component';
import { BucketComponent } from './bucket/bucket.component';



@NgModule({
  declarations: [
    AppComponent,
    AdminRole,
    RoleComponent,
    UserComponent,
    ProductComponent,
    CategoriesComponent,
    MarketComponent,
    AuthorizationComponent,
    PageComponent,
    RegistrationComponent,
    BucketComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
