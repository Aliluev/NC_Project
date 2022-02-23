import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule }   from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { UserComponentTest } from './user/user.component';
import { AdminRole } from './admin/admin.component';
import { RoleComponent } from './admin/role/role.component';
import { UserComponent } from './admin/user/user.component';
import { UserAddPage } from './admin/user/add-page/user-add-page.component';
import { UserDeletePage } from './admin/user/delete-page/user-delete-page.component';
import { UserUpdatePage } from './admin/user/update-page/user-update-page.component';




@NgModule({
  declarations: [
    AppComponent,
    UserComponentTest,
    AdminRole,
    RoleComponent,
    UserComponent,
    UserAddPage,
    UserDeletePage,
    UserUpdatePage
    
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
