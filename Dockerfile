FROM openjdk:8-jdk-alpine AS build
MAINTAINER vthota
RUN mkdir /app
COPY build/libs/employee-info-1.0.jar /app/employee-api.jar
ENTRYPOINT ["java","-jar","/app/employee-api.jar"]