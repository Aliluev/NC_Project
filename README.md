# NC_Project

![Entyties](https://github.com/Aliluev/NC_Project/blob/main/Entyties.png)

## Entyties
* User
* Roles
* Product
* Order
* Storage
* Category(Product)
* Status

### User
* id
* username
* password
* phone
* email

### Roles
* id
* role (Guest, Admin, Manager)

### Product 
* id
* price

### Order
* id
* user
* status

### Strorage
* product
* count

### Category
* id
* name 

### Status
* id
* status

### User-Role(composite key)
* userID
* roleID

### OrderList(composite key)
* orderID
* productID
* count

### Product-Category(composite key)
* productID
* categoryID


