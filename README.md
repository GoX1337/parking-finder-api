# parking-finder-api

Add you Bordeaux API key in : src/main/resources/application.yml

Build:
```
mvn clean install
```

Run:
```
java -jar target/parking-finder-api-0.0.1-SNAPSHOT.jar
```

Swagger doc:
```
http://localhost:8080/swagger-ui.html
```

Test:
```
GET http://localhost:8080/api/{city}/parking

GET http://localhost:8080/api/{city}/parking/near?latitude={latitude}&longitude={longitude}&distance={distance}
```
