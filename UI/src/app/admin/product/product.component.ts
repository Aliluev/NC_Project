import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Product } from "src/app/entities/product";

@Component({
    selector: 'product-component',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css']
})
export class ProductComponent {

    buttonAdd = false;
    buttonDelete = false;
    buttonUpdate = false;
    errorMessage = "";
    isLoginFailed = false;
    image: any;
    selectedFile: File | undefined;


    products2: Product[] = [];
    templateProduct: Product = new Product("", "", "", "", "");

    products = this.http.get<Product[]>('http://localhost:8080/product/get-all');
    product: Product = new Product("", "", "", "", "");
    productUpdate: Product = new Product("", "", "", "", "");
    name = "";

    fileLoad = "false";

    erorDelete = false;
    updateEror = false;

    onFileSelected(event: any) {
        this.selectedFile = <File>event.target.files[0];
        const fd = new FormData();
        fd.append('file', this.selectedFile, this.selectedFile?.name);
        this.http.post('http://localhost:8080/image/save', fd).subscribe((data: any) => {
            this.fileLoad = "true";
            console.log("ok")
        },
            (error: any) => console.log("eror"));
    }


    fileUpdate = "false";

    onFileSelectedUpdate(event: any) {
        this.selectedFile = <File>event.target.files[0];
        const fd = new FormData();
        fd.append('file', this.selectedFile, this.selectedFile?.name);
        this.http.post('http://localhost:8080/image/save', fd).subscribe((data: any) => {
            this.fileUpdate = "true";
            console.log("ok")
        },
            (error: any) => console.log("eror"));
    }


    methodDeleteProduct() {
        if (this.buttonDelete == false) {
            this.buttonDelete = true;
        } else {
            this.buttonDelete = false;
        }
    }

    methodUpdateProduct() {
        if (this.buttonUpdate == false) {
            this.buttonUpdate = true;
        } else {
            this.buttonUpdate = false;
        }
    }

    methodCanAddProduct() {
        if (this.buttonAdd == false) {
            this.buttonAdd = true;
        } else {
            this.buttonAdd = false;
        }

    }


    getProducts() {
        return this.http.get<Product[]>('http://localhost:8080/product/get-all');
    }

    loadProducts() {

        this.getProducts().subscribe((data: Product[]) => this.products2 = data);
    }





    deleteProduct(name: string | string) {
        this.http.delete('http://localhost:8080/product/delete/' + name).subscribe((data: any) => { console.log("ok"); },
            (error: any) => {
                console.log("eror");
                this.erorDelete = true;
                this.errorMessage = error.error.message;
            });
        this.products2 = this.products2.filter(c => (c.name !== name));
    }

    postProduct(product: Product) {
        product.image = this.fileLoad;
        this.http.post('http://localhost:8080/product/create', product).subscribe((data: any) => {
            console.log("ok");
            let temProduct = new Product(product.name, product.price, product.category, product.count, product.image);
            this.http.get<number>('http://localhost:8080/image/get-last-image').subscribe((data: number) => temProduct.image = "http://localhost:8080/image/" + data);
            this.products2.push(temProduct);
            this.isLoginFailed = false;
        },
            (error: any) => {
                console.log("eror");
                this.errorMessage = error.error.message;
                this.isLoginFailed = true;
            });


    }


    updateProduct(product: Product) {
        product.image = this.fileUpdate;
        this.http.put('http://localhost:8080/product/update'
            , product).subscribe((data: any) => {
                console.log("ok");
                let temProduct = new Product(product.name, product.price, product.category, product.count, product.image);
                this.http.get<number>('http://localhost:8080/image/get-last-image').subscribe((data: number) => temProduct.image = "http://localhost:8080/image/" + data);
                this.products2 = this.products2.filter(c => (c.name !== product.name));
                this.products2.push(temProduct);
            },
                (error: any) => {
                    console.log("eror");
                    this.updateEror = true;
                    this.errorMessage = error.error.message;

                });

    }


    constructor(private http: HttpClient) {
        this.loadProducts();
    }

}