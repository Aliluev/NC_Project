import { Component, OnInit } from "@angular/core";
import { AuthService } from "./authorization.service";
import { AuthLoginInfo } from "./login-info";
import { TokenStorageService } from "./token-storage.service";

@Component({
  selector: 'authorization-component',
  templateUrl: "./authorization.component.html",
  styleUrls: ["./authorization.component.css"]
})
export class AuthorizationComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo | undefined;
  buttonclick = false;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { this.isLoggedIn = false; }

  board: string = "";
  errorMessage2: string = "";

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    this.loginInfo = new AuthLoginInfo(
      this.form.username,
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.roles);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        this.buttonclick = true;
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }
}