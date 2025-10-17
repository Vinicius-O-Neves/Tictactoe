# ---- Build stage ----
FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build

# ---- Runtime stage ----
FROM eclipse-temurin:18-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
