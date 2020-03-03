# Saler Taxes Calculator:
A project to calculate a products sales taxes.

### RESTful API details:
```
http://localhost:8080/swagger-ui.html#/
```

#### Project description:  
* Project created with Spring Boot and Java 8
* Mysql and Spring Data JPA databases
* Unit tests with JUnit and Mockito

### How to run the application (Eclipse)
```
Right click on the 'SalesTaxesCalculatorApplication.java' class> Run as> Java application
```

### How to run the application (Docker)
```
docker run -d -p 8080:8080 augustols/api-app:latest
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

