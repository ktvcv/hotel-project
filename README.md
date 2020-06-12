# hotel-project
## This is system for reservation rooms in hotel
### System consists of three modules
+ One, implements Eureka-Server on Localhost:8871 port, where Eureka starts
+ One, implements Eureka-Client, which have connecting to db and services, on localhost:8080 port
+ One, Client-side application, on localhost:8888 port

## Requirements
#### For building and running application you will need
+ JDK 1.8+
+ Maven 4
+ PostgreSQL version 9.5+
+ pgAdmin2 Client Tool or pgAdmin4 Client Tool

```
## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/nameOfYourDB
spring.datasource.username=nameOfYour
spring.datasource.password=password
```

## Running the application localy
+ One way is to execute the main method in the 
uca\dss20192020\hotelproject\HotelProjectApplication class from your IDE.
+ Alternatively you can use the Spring Boot Maven plugin like so:
mvn spring-boot:run
