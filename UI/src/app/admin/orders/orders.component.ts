import { Component } from "@angular/core";
import { HttpClient, HttpParams } from '@angular/common/http';
import { Order } from "src/app/entities/order";
import { Bucket } from "src/app/entities/bucket";
import { environment } from "src/environments/environment";



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
        this.http.get<Order[]>(environment.backendUrl + '/order/get-ordered-order').subscribe((data: Order[]) => this.orders = data);

    }

    getDetailsOrders(id: string) {
        this.buttonClick = true;
        this.http.get<Bucket[]>(environment.backendUrl + '/order-list/get-order-list-ordered/' + id).subscribe((data: Bucket[]) => this.detailsOrders = data);

    }

} 