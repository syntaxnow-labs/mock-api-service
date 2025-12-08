FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw -B package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/mock-api-service-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]