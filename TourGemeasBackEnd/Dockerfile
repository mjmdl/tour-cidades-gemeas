# Use a imagem base do OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy as build

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivos do projeto
COPY . .

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Build do projeto
RUN mvn clean package -DskipTests

# Imagem final
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar o JAR do estágio de build
COPY --from=build /app/target/tourgemeas-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"] 