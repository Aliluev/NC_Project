import { Component } from "@angular/core";
import { HttpClient, HttpParams } from '@angular/common/http';
import { Order } from "src/app/entities/order";
import { Bucket } from "src/app/entities/bucket";



@Component({
    selector: 'orders-component',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})

export class OrdersComponent {

    constructor(private http: HttpClient) {
        this.getOrderes();
    }

    buttonClick = false;
    orders: Order[] = [];
    order: Order = new Order("", "", "", "");
    detailsOrders: Bucket[] = [];

    getOrderes() {
        this.http.get<Order[]>('http://localhost:8080/order/get-ordered-order').subscribe((data: Order[]) => this.orders = data);

    }

    getDetailsOrders(id: string) {
        this.buttonClick = true;
        this.http.get<Bucket[]>('http://localhost:8080/order-list/get-order-list-ordered/' + id).subscribe((data: Bucket[]) => this.detailsOrders = data);

    }


} 