FROM maven:3.9-eclipse-temurin-24-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn/ .mvn/
RUN ./mvnw dependency:go-offline --batch-mode
COPY src ./src
RUN mvn package -DskipTests --batch-mode

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]