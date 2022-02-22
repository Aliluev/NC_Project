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

@NgModule({
  declarations: [
    AppComponent,
    UserComponentTest,
    AdminRole,
    RoleComponent,
    UserComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
