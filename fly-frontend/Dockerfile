FROM openjdk:8u265-jre

ENV SERVER_PORT=8001

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=fly
ENV DB_USERNAME=develop
ENV DB_PASSWORD=123456

COPY ./target/fly-frontend-1.0-SNAPSHOT.jar /var/www/fly/application.jar

COPY ./src/main/resources/application-prod.yml /var/www/fly/application.yml

WORKDIR /var/www/fly

ENTRYPOINT mkdir -p /var/www/upload

CMD java -jar application.jar





