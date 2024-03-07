# Use uma imagem base com Java e Maven instalados
FROM maven:3.8-openjdk-17 AS builder

# Configure o diretório de trabalho
WORKDIR /app

# Copie o pom.xml para o diretório de trabalho
COPY pom.xml .

# Construa apenas as dependências do projeto para fins de cache
RUN mvn dependency:go-offline

# Copie todo o código-fonte do diretório src
COPY src ./src

# Construa o projeto
RUN mvn package -DskipTests

# Use uma imagem Java runtime leve
FROM openjdk:17-jdk-alpine

# Configure o diretório de trabalho
WORKDIR /app

# Copie o artefato construído do estágio de construção anterior
COPY --from=builder /app/target/DojoSpringMongo-0.0.1-SNAPSHOT.jar /app/DojoSpringMongo.jar

# Defina o comando padrão para executar o aplicativo Spring Boot
CMD ["java", "-jar", "DojoSpringMongo.jar"]
