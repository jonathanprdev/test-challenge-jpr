# Technical Test Ecommerce Inditex - Jonathan Patiño Rico:

This application is built according to the following requirements:


In the company's e-commerce database, we have the PRICES table that reflects the final price (retail price) and the rate that applies to a product of a chain between specific dates. Below is an example of the table with relevant fields:

PRICES
-------

BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE           CURR
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR

Fields:

BRAND_ID: Foreign key of the chain in the group (1 = ZARA).
START_DATE, END_DATE: Date range in which the indicated rate price applies.
PRICE_LIST: Identifier of the applicable price rate.
PRODUCT_ID: Product code identifier.
PRIORITY: Price application disambiguator. If two rates coincide in a date range, the one with the highest priority (highest numeric value) is applied.
PRICE: Final selling price.
CURR: Currency ISO code.

Required:

Build a SpringBoot application/service that provides a REST endpoint for querying, such that:

Accept input parameters: application date, product identifier, chain identifier.
Return output data: product identifier, chain identifier, applicable rate, application dates, and final price to apply.

Use an in-memory database (H2 type) and initialize it with the example data (you can change the field names and add new ones if needed, choose the data type that is considered appropriate for them).

Develop tests for the REST endpoint that validate the following requests to the service using the example data:

-          Test 1: Request at 10:00 on day 14 for product 35455 for brand 1 (ZARA)
-          Test 2: Request at 16:00 on day 14 for product 35455 for brand 1 (ZARA).
-          Test 3: Request at 21:00 on day 14 for product 35455 for brand 1 (ZARA).
-          Test 4: Request at 10:00 on day 15 for product 35455 for brand 1 (ZARA).
-          Test 5: Request at 21:00 on day 16 for product 35455 for brand 1 (ZARA).


Evaluation criteria:

- Service design and construction.
- Code quality.
- Correct results in the tests.

### Design Notes:

For the implementation of this service, a hexagonal architecture was used, considering its three main layers:

* Domain: This is the layer where all business entities are defined, representing the model. Different data transfer objects for the web layer or the input adapter are also defined here.
* Application: This layer is responsible for business logic and the use of infrastructure and domain. In this case, for the business model of the requirement, only a single port was defined, which is responsible for using the persistence adapter to find the corresponding rate based on input data.
* Infrastructure: In this layer, two adapters were defined: one for input, which is the web controller of the API, and one for output, which corresponds to persistence in H2 database.

### To run the service as a Java application from the console, use the following command:

-          mvn spring-boot:run

### The application runs on port 8080.

### To view the Swagger documentation of the implemented API, you can access the following URL:

 -          http://localhost:8080/swagger-ui/index.html

### To access the H2 console and connect to the database, use the following details:
-          http://localhost:8080/h2-console/
  - Driver class: org.h2.Driver
  - jdbc:h2:mem:ecommercedb
  - User Name: sa
  - Password: pwd

### To run integration tests with the proposed test cases, use the following command:

-          mvn test

# Deployment application in a Docker Container

### 1. Compile and package the application using the following command:
-          mvn clean install

### 2. Build the application image by navigating to the project root and using the following command:
-          docker-compose build

### 3. Run the application in the container generated in step 2 using the following command:
-          docker-compose up -d

### The application is deployed on port 8080.

### To view the Swagger documentation of the implemented API, you can access the following URL:

-          http://localhost:8080/swagger-ui/index.html

### You can find a Postman collection with the 5 proposed test cases at the following path:

-          {project path}/src/main/resources/static/Inditex Technical Test Collection.postman_collection.json