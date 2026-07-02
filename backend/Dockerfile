# Build stage
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN mvn -DskipTests clean package

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]