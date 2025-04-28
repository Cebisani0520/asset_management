FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /build

# Copy the parent pom.xml
COPY pom.xml ./pom.xml

# Copy the common-lib module (in case eureka-server uses it too)
COPY common-lib/ ./common-lib/

# Copy the department-service module
COPY department-service/ ./department-service/

# Copy the eureka-server module
COPY eureka-server/ ./eureka-server/

# Build the parent project
RUN mvn clean package spring-boot:repackage -DskipTests

# Runtime stage
FROM eclipse-temurin:21-alpine

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /build/eureka-server/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
