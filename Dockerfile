FROM openjdk:21-slim
WORKDIR /app
COPY ./target/spring-tutorial-1.0.0.jar ./app.jar
CMD ["java", "-jar", "app.jar"]