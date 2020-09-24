FROM openjdk:8

WORKDIR /home/travis/build/peng49/fly

COPY ./fly-frontend/target/fly-frontend-1.0-SNAPSHOT.jar /var/www/fly/application.jar




