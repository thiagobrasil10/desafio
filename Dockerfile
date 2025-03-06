# Etapa de build
FROM gradle:8.12.1-jdk21 AS build

WORKDIR /app

COPY . .

# Executa os testes e imprime os resultados no console
RUN gradle test --no-daemon --debug

RUN gradle build --no-daemon

# Etapa de execução
FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/application.jar"]