FROM openjdk:22-slim-bullseye

WORKDIR /app

COPY target/users-crud-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "users-crud-0.0.1-SNAPSHOT.jar"]
