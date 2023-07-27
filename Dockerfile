FROM alpine:latest as build

RUN apk update
RUN apk add openjdk17

COPY . /app
WORKDIR /app
RUN mvn package

FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build /app/target/buensabor-stable.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]