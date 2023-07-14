FROM maven:3.8.6-openjdk-8 as builder

WORKDIR /var/www/fly

COPY . /var/www/fly

RUN mvn clean && mvn package -am -amd -pl fly-web -pl fly-admin -DskipTests=true


FROM openjdk:8u265-jre

ENV SERVER_PORT=8001

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=fly
ENV DB_USERNAME=root
ENV DB_PASSWORD=password

COPY --from=builder /var/www/fly/fly-web/target/fly-web-1.0-SNAPSHOT.jar /var/www/fly-web/application.jar
COPY --from=builder /var/www/fly/fly-web/src/main/resources/application-prod.yml /var/www/fly-web/application.yml

COPY --from=builder /var/www/fly/fly-admin/target/fly-admin-1.0-SNAPSHOT.jar /var/www/fly-admin/application.jar
COPY --from=builder /var/www/fly/fly-admin/src/main/resources/application-prod.yml /var/www/fly-admin/application.yml

