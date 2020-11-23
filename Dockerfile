# Pull base image.
FROM openjdk:8-jdk-alpine

ARG PORT=80
ARG JAR_FILE=target/*.jar
ARG PROFILE=prod

# VOLUME /logs
WORKDIR /
COPY ${JAR_FILE} app.jar

EXPOSE ${PORT}

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE}  -DPORT=${PORT}","-jar","/app.jar"]
