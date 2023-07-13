FROM maven:3.8.6-openjdk-8 as builder

WORKDIR /var/www/fly

COPY . /var/www/fly

RUN mvn clean -Dorg.slf4j.simpleLogger.defaultLogLevel=error && mvn package -am -amd -DskipTests=true -Dorg.slf4j.simpleLogger.defaultLogLevel=error


FROM openjdk:8u265-jre

ENV SERVER_PORT=8001

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=fly
ENV DB_USERNAME=develop
ENV DB_PASSWORD=123456

COPY --from=builder /var/www/fly/fly-frontend/target/fly-frontend-1.0-SNAPSHOT.jar /var/www/frontend/applicatoin.jar

COPY --from=builder /var/www/fly/fly-admin/target/fly-admin-1.0-SNAPSHOT.jar /var/www/admin/application.jar

