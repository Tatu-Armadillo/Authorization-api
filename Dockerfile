FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-noble
COPY --from=build /app/target/authorization-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]