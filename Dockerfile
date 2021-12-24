FROM openjdk:8-jdk-alpine AS build
MAINTAINER chthota
EXPOSE 8090:8090
RUN mkdir /app
COPY build/libs/*.jar /app/employee-api-*.jar
ENTRYPOINT ["java","-jar","/app/employee-api-*.jar"]

