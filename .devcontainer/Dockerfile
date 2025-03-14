FROM eclipse-temurin:17-jdk
# Instalar Maven y Gradle
# Crear el usuario "vscode" con permisos adecuados

ENV GRADLE_VERSION=8.12.1

RUN apt-get update && \
    apt-get install -y unzip curl && \
    curl -SL "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o gradle.zip && \
    unzip gradle.zip -d /opt && \
    rm gradle.zip && \
    ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/local/bin/gradle
    
RUN apt-get update &&  apt-get install -y git

RUN useradd -m -s /bin/bash vscode

# Definir el usuario predeterminado
USER vscode
