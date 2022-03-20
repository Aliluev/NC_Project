
import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})


export class UserService implements OnInit {

  private userUrl = environment.backendUrl + '/api/test/user';
  private pmUrl = environment.backendUrl + '/api/test/mod';
  private adminUrl = environment.backendUrl + '/api/test/admin';

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  info: any;
  ngOnInit(): void {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }


  getUserBoard(): Observable<string> {
    var headers = new HttpHeaders({
      Authorization: 'Bearer ' + this.info.token
    });
    const httpOptions = {
      headers: headers
    };
    return this.http.get<string>(this.userUrl, httpOptions);

  }




  getPMBoard(): Observable<string> {
    return this.http.get(this.pmUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }
}