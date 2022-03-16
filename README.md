# E-Pood

### Tools

1. JDK 11.x - https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
2. Maven 4.0.0 - https://maven.apache.org/docs/4.0.0/release-notes.html
3. MySQL 8 - https://dev.mysql.com/doc/relnotes/mysql/8.0/en/
4. Angular 13.2.6 - https://angular.io/docs

## Building and running the project

Install MySQl and create local database.

Change in application.properties username and password.

To build project run:
```
mvn clean install
```

Then run the BackEnd using the command:
```
java -jar E-Pood-BackEnd/target/e-pood.jar
```

For the run of FrontEnd part, move into E-Pood-Frontend:
```
cd E-Pood-Frontend/
```
And then run it using the command:
```
ng serve
```