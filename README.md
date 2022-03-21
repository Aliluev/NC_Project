# NC_Project

![Entyties](https://github.com/Aliluev/NC_Project/blob/main/finalEntities.png)

Entity Shop removed and added count to Product Entity

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
* count

### Order
* id
* userID
* statusID
* data


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


