import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
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
    addEror = false;
    deleteEror = false;
    errorMessage = "";

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
    }

    deleteCategory(name: string | string) {
        this.http.delete(environment.backendUrl + '/category/delete/' + name).subscribe((data: any) => {
            console.log("ok");
            if (this.deleteEror = true) {
                this.deleteEror = false;
            }
        },
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
            if (this.addEror = true) {
                this.addEror = false;
            }
        },
            (error: any) => {
                console.log("eror");
                this.errorMessage = error.error.message;
                this.addEror = true;
            });
    }

}