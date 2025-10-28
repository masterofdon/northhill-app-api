FROM maven:3.9.8-eclipse-temurin-22

COPY config/. /opt/app/.
COPY app.jar /opt/app/app.jar

ENV SPRING_PROFILES_ACTIVE=production
ENV PROFILE=production

ENV DB_HOST="10.128.15.198"
ENV DB_NAME="northhilldb"
ENV DB_USERNAME="root"
ENV DB_PASSWORD="pED18ggA!"

WORKDIR /opt/app
EXPOSE 8080
CMD ["java" , "-jar" , "app.jar"]