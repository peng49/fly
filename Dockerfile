FROM openjdk:8u265-jre

COPY ./fly-frontend/target/fly-frontend-1.0-SNAPSHOT.jar /var/www/fly/application.jar

COPY ./fly-frontend/src/main/resources/application-prod.yml /var/www/fly/application.yml

WORKDIR /var/www/fly

CMD java -jar application.jar





