FROM openjdk:17

ADD target/springboot-mongo-docker.jar springboot-mongo-docker.jar
ENTRYPOINT ["java","-jar","/springboot-mongo-docker.jar"]