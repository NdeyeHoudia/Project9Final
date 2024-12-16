FROM openjdk:17

WORKDIR /app

COPY target/* /app/springboot-gateway-docker.jar
EXPOSE 8761

ENTRYPOINT ["java","-jar","/springboot-gateway-docker.jar"]

