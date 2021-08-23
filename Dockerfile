FROM openjdk:8-jdk-alpine
VOLUME  "/app" 
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]