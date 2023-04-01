# Hillel Spring WebMVC demo
An example app running on Tomcat 10

## Tool and frameworks used:
- Spring Core, Spring WebMVC
- JPA/Hibernate
- Tomcat 10
- MySQL
- H2 Database
- Slf4j + Log4j2
- Jackson
- Mapstruct
- Lombok 
- JUnit
- Mockito

## Prerequisites
- Java >= 17
- Tomcat 10
- Maven


## Build
1. Set database credentials either by using `app.properties` file
```properties
# ...
jpa.hibernate.mysql.url=jdbc:mysql://hostname:port/dbname
jpa.hibernate.mysql.username=username
jpa.hibernate.mysql.password=password

# other props ...
```

or by setting following env variables: `MYSQL_URL`, `MYSQL_USER`, `MYSQL_PASSWORD`

2. Build a `war` archive using Maven:
```shell
mvn clean package
```
2. Find `war` file in `target/springmvc.demo-{version}.war`

## Deploy and run
- To run locally deploy created `war` file on Tomcat 10
- App is now available at [http://localhost:8080/{contextPath}/]()


