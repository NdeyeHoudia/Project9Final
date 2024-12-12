#FROM ubuntu:latest
# Use an OpenJDK  as a parent image

FROM openjdk:24-slim-bullseye

WORKDIR /app


ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/springboot-mysql-docker.jar

#EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/springboot-mysql-docker.jar"]