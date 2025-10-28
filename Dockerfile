# ---- Build stage ----
FROM maven:3.9.8-eclipse-temurin-22 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package -DfinalName=app

# ---- Runtime stage ----

FROM eclipse-temurin:22-jre-alpine
WORKDIR /opt/app
COPY config/. /opt/app/.
COPY --from=build /workspace/target/app.jar /opt/app/app.jar
ENV SPRING_PROFILES_ACTIVE=production
ENV PROFILE=production

ENV DB_HOST="10.128.15.198"
ENV DB_NAME="northhilldb"
ENV DB_USERNAME="root"
ENV DB_PASSWORD="pED18ggA!"

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]