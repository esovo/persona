FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY build/libs/*.jar app.jar
COPY application-connection.yml application.yml
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.config.location=/app/application.yml","-jar","/app/app.jar"]
