import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Role } from "src/app/entities/role";

@Component({
    selector: 'role-component',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.css']
})
export class RoleComponent{

    buttonAdd=false;
    buttonDelete=false;
   
   roles:Role[]=[];
   templateRole:Role=new Role(0,"");
    // roles= this.http.get<Role[]>('http://localhost:8080/role/get-all-role');
    role:Role=new Role(0,"");
    name="";


    getRoles(){
        return this.http.get<Role[]>('http://localhost:8080/role/get-all-role');
      }

      loadRoles(){
        
        this.getRoles().subscribe((data: Role[])=>this.roles=data); 
    }



    methodCanAddRole(){
        if(this.buttonAdd==false){
            this.buttonAdd=true;
        }else {
            this.buttonAdd=false;
        }      
    }
    
    methodDeleteRole(){
        if(this.buttonDelete==false){
            this.buttonDelete=true;
        }else{
            this.buttonDelete=false;
        }
    }


    deleteRole(name: string | string){
        this.http.delete('http://localhost:8080/role/delete/'+name
        //{
        // params: new HttpParams().set('', id)}
        ).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
        this.roles=this.roles.filter(c=>(c.name !== name));
      }
    
      postRole(role: Role){
        this.http.post('http://localhost:8080/role/create',role).subscribe((data:any) => {console.log("ok")},
          (error: any)=> console.log("eror"));
          let temRole=new Role(role.id,role.name);
          this.roles.push(temRole);
      }


    constructor(private http: HttpClient){this.loadRoles(); };
}
