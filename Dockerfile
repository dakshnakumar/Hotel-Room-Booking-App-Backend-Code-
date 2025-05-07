# ------------ Build Stage ------------
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy everything and build the app
COPY . .
RUN mvn clean package -DskipTests

# ------------ Run Stage ------------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy only the built JAR
COPY --from=build /app/target/*.jar app.jar

# Set the PORT environment variable (Render uses this)
ENV PORT=8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
