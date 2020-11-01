FROM openjdk:8u265-jre

ENV SERVER_PORT=8001

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=fly
ENV DB_USERNAME=develop
ENV DB_PASSWORD=123456

COPY ./target/fly-admin-1.0-SNAPSHOT.jar /var/www/fly-admin/fly-admin.jar

COPY ./src/main/resources/application-prod.yml /var/www/fly-admin/application.yml

WORKDIR /var/www/fly-admin

CMD java -jar fly-admin.jar