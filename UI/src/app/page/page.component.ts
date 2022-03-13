import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { TokenStorageService } from "../authorization/token-storage.service";
import { UserService } from "../authorization/user.service";
import { Product } from "../entities/product";



@Component({
    selector: 'page-component',
    templateUrl: "./page.component.html",
    styleUrls: ["./page.component.css"]
})
export class PageComponent implements OnInit{

    board: string;
  errorMessage: string;
 
  constructor(private userService: UserService,private token: TokenStorageService, private http: HttpClient) {
      this.board="";
      this.errorMessage="";
      
   }
 

   private userUrl = 'http://localhost:8080/api/test/user';

   info: any;

  // 'Authorization': 'Bearer ' + token,
 //    'Content-Type': 'application/json'


  //stri='Bearer '+this.token.getToken();
  image="";

  //this.http.get<number>('http://localhost:8080/image/get-last-image').subscribe((data: number)=>image="http://localhost:8080/image/"+data);
  stri="";
  ss=""
  //stri:string="sss";

   getUserBoard() {
    const headerss= new HttpHeaders({ 'Content-Type': 'application/json', 
  Authorization:this.stri});
  const httpOptions = {
    headers: headerss
  };
  console.log(this.stri);
   this.http.get<string>(this.userUrl).subscribe((data)=>this.ss=data);

  }


  ngOnInit() {

    this.info = {
        token: this.token.getToken(),
        username: this.token.getUsername(),
        authorities: this.token.getAuthorities()
      };

/*

    this.userService.getUserBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
    */
  }
    
}