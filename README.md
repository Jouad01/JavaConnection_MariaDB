# JavaConnection-MariaDB

This project is a Java application that uses the JDBC library to connect to a MariaDB database. 
It also uses the JPA Hibernate to interact with the database and the Connection Pool design pattern 
to manage connections.

---

## Requirements

- Java 8 or higher
- MariaDB 10.3.8
- Maven 3.5.4
- Hibernate 5.2.17.Final
- MYSQL
- Docker 18.09.0 or higher

---
## Tools used 

### Maven

- To compile and build the project with Maven, run mvn clean package in the root of the project.
- To run the tests, run mvn test at the root of the project.

### MariaDB

- To use MariaDB, you must install it on your system or use the mariadb:10.3.31 Docker container. If you want to use the Docker container, run docker pull mariadb:10.3.31 to download it.
- The script.sql file contains the schema and data to create the database needed by the application.

### JPA Hibernate

- The User class in User.java represents the users table in the database.
- The src/main/resources/META-INF/persistence.xml file contains the database and Hibernate configuration.

### Docker

- To build the Docker image, run docker build -t javaconnectmariadb . in the root of the project. Make sure you have Docker installed on your system.
- To download the Docker image from Docker Hub, run docker pull jouad01/javaconnectmariadb
- To run the application in a Docker container, run docker run -p 8080:8080 --name javaconnectmariadb javaconnectmariadb .

---

## Steps to run the application

1. Create database and tables
2. Update the database configuration in the persistence.xml file
3. Clone the project and run mvn clean package or mvn compile
4. Run the application (files JDBC_POOL or Main)