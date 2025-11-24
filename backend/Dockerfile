FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el JAR generado
COPY target/habittracking-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
