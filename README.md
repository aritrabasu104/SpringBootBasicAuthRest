# SpringBootBasicAuthRest
Sample spring boot app with basic auth and cors enabled for serving rest
This is a sample Java / Maven / Spring Boot application for providing example of basic auth with CORS filters enabled and in memory db from apache derby
The username and password is 
``` 
user1
password1
```
## How to Run 

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run 
