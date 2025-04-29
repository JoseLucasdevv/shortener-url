FROM maven:3.9-eclipse-temurin-24-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn/ .mvn/
RUN ./mvnw dependency:go-offline --batch-mode
COPY src ./src
RUN mvn package

FROM gcr.io/distroless/java21-debian12
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80.0", "-jar", "/app/app.jar"]
