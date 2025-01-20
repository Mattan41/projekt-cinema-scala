# Stage 1: Build the backend application
FROM --platform=$BUILDPLATFORM maven:3.9.9-eclipse-temurin-23-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the backend application
FROM --platform=$BUILDPLATFORM eclipse-temurin:23-jdk-alpine
VOLUME /tmp
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# OBS! Bygg och pusha båda Docker-images till Docker Hub med;
#docker buildx build --platform linux/arm/v8 -t mattan41/backendcinemascalaforrpi:latest --push ./backend
