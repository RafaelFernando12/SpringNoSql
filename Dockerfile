    FROM maven:3.8-openjdk-17 AS builder

    WORKDIR /app

    COPY ../pom.xml ./

    RUN mvn dependency:go-offline

    COPY ../src ./src

    RUN mvn package -DskipTests

    FROM openjdk:17-jdk-alpine

    WORKDIR /app

    COPY --from=builder /app/target/DojoSpringMongo-0.0.1-SNAPSHOT.jar /app/DojoSpringMongo.jar

    EXPOSE 8080

    CMD ["java", "-jar", "DojoSpringMongo.jar"]