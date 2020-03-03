# Saler Taxes Calculator:
A project to calculate a products sales taxes.

#### Project description:  
* Project created with Spring Boot and Java 8
* Mysql and Spring Data JPA databases
* Unit tests with JUnit and Mockito

### How to run the application (Eclipse)
```
Right click on the > 'SalesTaxesCalculatorApplication.java' class > Run as > Java application
```

### How to run the application (Maven)
```
Make sure you have Maven installed and added to your operating system's PATH, as well as Git and Java 8.

git clone https://github.com/duarterafael/SalesTaxesCalculator.git
cd SalesTaxesCalculator/ && cd backend/implementation
mvn spring-boot:run
```

### RESTful API details:
```
http://localhost:8080/swagger-ui.html#/
```

### TIPS
* If your eclipse show not recognize the getts and setts, please see the solutions here: 
https://stackoverflow.com/questions/45461777/lombok-problems-with-eclipse-oxygen

* If the unit tests not working, please see the solutions here: 
https://stackoverflow.com/questions/46717693/eclipse-no-tests-found-using-junit-5-caused-by-noclassdeffounderror-for-launcher

* You can call API using postman collection. Available here: https://github.com/duarterafael/SalesTaxesCalculator/tree/master/backend/documentation/postman

* You can see the ER here: 
![ER](https://github.com/duarterafael/SalesTaxesCalculator/blob/master/backend/documentation/ER/ER.png)
Finally, data modeling follows a structure from 1 to N. Where the product_type table contains tax and product contains the product name. In this way, if the tax of a product type is changed, all products related to it have been readjusted.

### Solution description
* To meet the specifications of the challenge, this application could be developed stand alone. However, it would not have an easy way or that it would not have to restart the application to inserting new products and product types. 
* Based on this assumption, the application was developed in a RESTFul architecture with CRUD for the product and product type entities. In this way it is possible to dynamically change (without having to restart the application) creating new products, changing the tax of an existing product or even removing a product or product type.
* In this way the application becomes extensible for the registration of these entities, but it is also extensible at the code level for new entities. For this purpose, just implement the interfaces ICRUDController (for controller layer) and ICRUDService (for business layer). When implementing this interface, the programmer will have to implement the crud methods following the unique signature following the defined architectural standardization.
* The application has the class 'DataLoader' that makes the initial population of the database with the challenge data. If you wanted to insert new user data the APIs:
Access the product endpoint: http://localhost:8080/api/v1/products
Access the product type endpoint: http://localhost:8080/api/v1/products/types
for more information on the APIs access the swagger here: http://localhost:8080/swagger-ui.html#/
* Acess the sales taxes calculator endpoint: http://localhost:8080/api/v1/sales/taxes




