# SweetHome


## How to build & run the app:
### 1. Start the database:
```
    docker-compose -f docker-compose.yml -p app-db up -d
```
### 2. Start the backend:
```
    cd backend
    mvn clean install
    mvn spring-boot:run
```
### 2. Start the frontend:
```
    cd frontend
    npm install
    npm run dev
```
### Access backend API documentation at: http://localhost:8090/swagger-ui/index.html
### Access the frontend app at: http://localhost:5173/
### Use the following credentials for login:
```
    email: admin@admin.com
    password: admin
```