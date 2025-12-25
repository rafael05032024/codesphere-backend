# ====== BUILD ======
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia wrapper e pom primeiro (cache de dependências)
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copia o código
COPY src src

RUN ./mvnw package -DskipTests

# ====== RUNTIME ======
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/quarkus-app/ ./

EXPOSE 8080

CMD ["java", "-jar", "quarkus-run.jar"]