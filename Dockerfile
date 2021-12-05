FROM openjdk:8-jdk-alpine AS build
MAINTAINER vthota
EXPOSE 8090:8090
RUN mkdir /app
COPY build/libs/employee-info-1.0.jar /employee-api.jar
ENTRYPOINT ["java","-jar","/employee-api.jar"]