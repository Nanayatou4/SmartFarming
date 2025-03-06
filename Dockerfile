FROM ubuntu:latest
LABEL authors="moussa"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk-slim

# Définition du répertoire de travail
WORKDIR /app

# Copie du fichier JAR dans le conteneur
COPY target/*.jar app.jar

# Exposition du port de l'application
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
