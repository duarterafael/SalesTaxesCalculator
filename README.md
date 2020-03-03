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

### How to run the application (Docker)
```
git clone https://github.com/duarterafael/SalesTaxesCalculator.git
cd SalesTaxesCalculator/
docker-compose up -d
```

### Como executar a aplicação (Maven)
```
Make sure you have Maven installed and added to your operating system's PATH, as well as Git and Java 8.

git clone https://github.com/duarterafael/SalesTaxesCalculator.git
cd SalesTaxesCalculator/ && cd api
mvn spring-boot:run

* Access the product endpoint: http://localhost:8080/api/v1/products
* Access the product type endpoint: http://localhost:8080/api/v1/products/types
* Acess the sales taxes calculator endpoint: http://localhost:8080/api/v1/sales/taxes
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

* You can see the ER here: https://github.com/duarterafael/SalesTaxesCalculator/blob/master/backend/documentation/ER/ER.png

