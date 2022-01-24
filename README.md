# NC_Project_Entities

![Entyties](https://github.com/Aliluev/NC_Project/blob/main/Entities2.png)

## Entities
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
* name (Guest, Admin, Manager)

### Product 
* id
* name
* price

### Order
* id
* userID
* statusID
* data

### Strorage
* id
* productID
* count
* address

### Category
* id
* name 

### Status
* id
* name

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


