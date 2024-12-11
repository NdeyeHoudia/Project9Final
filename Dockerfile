#FROM ubuntu:latest
# Use an OpenJDK Runtime as a parent image


FROM openjdk:24-slim-bullseye

WORKDIR /app

#ARG JAR_FILE=target/*.jar

COPY target/*.jar /app/demoApp.jar
#COPY ${JAR_FILE} /app/greeting.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/demoApp.jar"]

