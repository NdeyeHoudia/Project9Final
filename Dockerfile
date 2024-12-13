FROM openjdk:24-slim-bullseye

WORKDIR /app

COPY target/* /app/springboot-mysql-docker.jar

ENTRYPOINT ["java", "-jar", "/app/springboot-mysql-docker.jar"]