FROM eclipse-temurin:17-jdk
# Instalar Maven y Gradle
# Crear el usuario "vscode" con permisos adecuados

RUN apt-get update && apt-get install -y gradle

RUN useradd -m -s /bin/bash vscode

# Definir el usuario predeterminado
USER vscode
