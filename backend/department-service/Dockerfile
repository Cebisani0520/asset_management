FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /build

# Copy the parent pom.xml
COPY pom.xml ./pom.xml

# Copy the common-lib module
COPY common-lib/ ./common-lib/

# Copy the department-service module
COPY department-service/ ./department-service/

# Build the parent project
# Using a single Maven command that includes package and spring-boot:repackage
RUN mvn clean package spring-boot:repackage -DskipTests

# Runtime stage
FROM eclipse-temurin:21-alpine

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /build/department-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]