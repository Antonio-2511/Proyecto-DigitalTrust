# Imagen base con Java 17 (compatible con Spring Boot 3)
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el jar generado por Maven
COPY target/*.jar app.jar

# Puerto interno de la aplicación
EXPOSE 8080

# Arranque de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]