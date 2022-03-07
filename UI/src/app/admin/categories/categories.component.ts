import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Category } from "src/app/entities/category";

@Component({
    selector: 'categories-component',
    templateUrl: "./categories.component.html",
    styleUrls: ["./categories.component.css"]
})
export class CategoriesComponent{

    buttonAdd=false;
    buttonDelete=false;
    

    selectedFile :File|undefined;

    onFileSelected(event: any){
        this.selectedFile = <File>event.target.files[0];
        const fd = new FormData();
        fd.append('file',this.selectedFile, this.selectedFile?.name );
        this.http.post('http://localhost:8080/image/save',fd).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
    }

    image:any;

    loadImage(){
        this.http.get('http://localhost:8080/image/1',this.image).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
      //  var a = document.createElement("a");
       // this.image=3;
        
    }

    /*
    onUpload(){
        const fd = new FormData();
        fd.append('image',this.selectedFile, this.selectedFile?.name )
        this.http.post('http://localhost:8080/image/save',this.selectedFile)
    }
    */


    methodDeleteCategory(){
        if(this.buttonDelete==false){
            this.buttonDelete=true;
        }else{
            this.buttonDelete=false;
        }
    }

    methodCanAddCategory(){
        if(this.buttonAdd==false){
            this.buttonAdd=true;
        }else {
            this.buttonAdd=false;
        }
        
    }

    categories:Category[]=[];
    templateCategory:Category=new Category("","");
    name="";

    getCategories(){
        return this.http.get<Category[]>('http://localhost:8080/category/get-all');
      }

      loadCategories(){
        
        this.getCategories().subscribe((data: Category[])=>this.categories=data); 
    }

    constructor(private http: HttpClient){ 
        this.loadCategories();
        this.loadImage();
    }

    deleteCategory(name: string | string){
        this.http.delete('http://localhost:8080/category/delete/'+name).subscribe((data:any) => {console.log("ok")},
        (error: any)=> console.log("eror"));
   // this.http.get<Product[]>('http://localhost:8080/product/get-all').subscribe((data: Product[])=>this.products2=data);  
    this.categories=this.categories.filter(c =>(c.name !== name));  
   }

   postCategory(category: Category){
    this.http.post('http://localhost:8080/category/create',category).subscribe((data:any) => {console.log("ok")},
      (error: any)=> console.log("eror"));
      //this.loadProducts();
      let temCategory=new Category(category.name,category.product);
      this.categories.push(temCategory);
  }

}