# SweetHome


### How to build & run the app:
```shell showLineNumbers
    docker-compose -f docker-compose.yml -p app-db up -d
    mvn clean install
    mvn spring-boot:run
```

#### Access API documentation at: http://localhost:8090/swagger-ui/index.html
