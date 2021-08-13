#FROM openjdk:8
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} ServiceCenterApplication.jar
#ENTRYPOINT ["java","-jar","/ServiceCenterApplication.jar"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]