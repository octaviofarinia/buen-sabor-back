FROM maven:3.8.5-openjdk-17 as build

COPY . /app
WORKDIR /app
RUN mvn package

FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build /app/target/buensabor-stable.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]