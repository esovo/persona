FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
ARG APP_CONFIG
COPY ${APP_CONFIG} application-connection.yml
COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
