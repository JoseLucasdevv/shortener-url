# V1 Shortener URL With SpringBoot

# URL Shortener API

A simple API service that converts long URLs into shorter, more manageable links with expiration tracking.

## How Run the Application

### Via Docker

     1. Run the command "sudo docker build -t 'Image-name' ." in the same level from Dockerfile.

     2. Run the command "sudo docker run -d -p 8080:8080  --name "container-name" 'Image-name'"

     3. check if the container is running "sudo docker ps"

### Via Maven

    1. Build the project using the follow command "mvn clean package",it should be used in the same level from pom.xml
    2. after let's run it using the follow command "java -jar target/shortener-0.0.1-SNAPSHOT.jar"

## Base URL

`http://localhost:8080`

## API Documentation

Interactive API documentation available via Swagger UI:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Endpoints

### Shorten a URL

**POST** `/api/v1/shortener`

Converts a long URL into a shortened version with tracking information.

#### Request

```json
{
  "url": "http://www.google.com"
}
```

#### Response

```json
{
  "shortUrl": "string",
  "OriginalUrl": "string",
  "uid": "string",
  "expiresAt": "2025-04-29T17:03:40.303Z",
  "createdAt": "2025-04-29T17:03:40.303Z"
}
```
