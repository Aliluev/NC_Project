import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Observable } from "rxjs";
import { Category } from "src/app/entities/category";
import { environment } from "src/environments/environment";

@Component({
    selector: 'categories-component',
    templateUrl: "./categories.component.html",
    styleUrls: ["./categories.component.css"]
})
export class CategoriesComponent {

    buttonAdd = false;
    buttonDelete = false;

    username = "";
    password = "";

    selectedFile: File | undefined;

    addEror = false;
    deleteEror = false;
    errorMessage = "";

    onFileSelected(event: any) {
        this.selectedFile = <File>event.target.files[0];
        const fd = new FormData();
        fd.append('file', this.selectedFile, this.selectedFile?.name);
        this.http.post(environment.backendUrl + '/image/save', fd).subscribe((data: any) => { console.log("ok") },
            (error: any) => console.log("eror"));
    }

    image: any;

    loadImage() {
        this.http.get(environment.backendUrl + '/image/1', this.image).subscribe((data: any) => { console.log("ok") },
            (error: any) => console.log("eror"));
    }

    clickBackend(username: string, password: string) {
        this.http.post(environment.backendUrl + '/login/login', { username, password }).subscribe((data: any) => { console.log("ok") },
            (error: any) => console.log("eror"));
    }

    Pampam() {
        this.http.post<Observable<boolean>>(environment.backendUrl + '/api/auth/signin', {
            username: this.username,
            password: this.password
        }).subscribe((data: any) => { console.log("ok") },
            (error: any) => console.log("eror"));
    }



    perem = "";
    Uploadd() {
        this.http.get<string>(environment.backendUrl + '/user').subscribe((data: string) => this.perem = data);

    }



    methodDeleteCategory() {
        if (this.buttonDelete == false) {
            this.buttonDelete = true;
        } else {
            this.buttonDelete = false;
        }
    }

    methodCanAddCategory() {
        if (this.buttonAdd == false) {
            this.buttonAdd = true;
        } else {
            this.buttonAdd = false;
        }

    }

    categories: Category[] = [];
    templateCategory: Category = new Category("", "");
    name = "";

    getCategories() {
        return this.http.get<Category[]>(environment.backendUrl + '/category/get-all');
    }

    loadCategories() {

        this.getCategories().subscribe((data: Category[]) => this.categories = data);
    }

    constructor(private http: HttpClient) {
        this.loadCategories();
        this.loadImage();
    }

    deleteCategory(name: string | string) {
        this.http.delete(environment.backendUrl + '/category/delete/' + name).subscribe((data: any) => { console.log("ok") },
            (error: any) => {
                this.deleteEror = true;
                console.log("eror");
                this.errorMessage = error.error.message;


            });
        this.categories = this.categories.filter(c => (c.name !== name));
    }

    postCategory(category: Category) {
        this.http.post(environment.backendUrl + '/category/create', category).subscribe((data: any) => {
            console.log("ok");
            let temCategory = new Category(category.name, category.product);
            this.categories.push(temCategory);
        },
            (error: any) => {
                console.log("eror");
                this.errorMessage = error.error.message;
                this.addEror = true;
            });


    }

}