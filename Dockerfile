# Etapa 1: Build con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar primero el pom.xml y descargar dependencias (para usar caché)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución ligera con JDK 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
