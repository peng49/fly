FROM maven:3.8.6-openjdk-8 as builder

WORKDIR /usr/src/mymaven

COPY . /usr/src/mymaven

RUN apt update && apt install nodejs npm -y && npm -version && \
    cd fly-admin/src/main/resources/vue && npm install && npm run build:prod && \
    cd /usr/src/mymaven && mvn clean && mvn package -am -amd -pl fly-web -pl fly-admin -DskipTests=true

FROM openjdk:8u265-jre

WORKDIR /var/www/

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=fly
ENV DB_USERNAME=root
ENV DB_PASSWORD=password

#RUN --mount=src=/usr/src/mymaven,dst=/tmp/dst,from=builder mkdir -p /var/www/fly-web /var/www/fly-admin /var/www/upload && \
#    cp /tmp/dst/fly-web/target/fly-web-1.0-SNAPSHOT.jar /var/www/fly-web/application.jar && \
#    cp /tmp/dst/fly-web/src/main/resources/application-prod.yml /var/www/fly-web/application.yml && \
#    cp /tmp/dst/fly-admin/target/fly-admin-1.0-SNAPSHOT.jar /var/www/fly-admin/application.jar && \
#    cp /tmp/dst/fly-admin/src/main/resources/application-prod.yml /var/www/fly-admin/application.yml

COPY --from=builder /usr/src/mymaven/fly-web/target/fly-web-1.0-SNAPSHOT.jar /var/www/fly-web/application.jar
COPY --from=builder /usr/src/mymaven/fly-web/src/main/resources/application-prod.yml /var/www/fly-web/application.yml

COPY --from=builder /usr/src/mymaven/fly-admin/target/fly-admin-1.0-SNAPSHOT.jar /var/www/fly-admin/application.jar
COPY --from=builder /usr/src/mymaven/fly-admin/src/main/resources/application-prod.yml /var/www/fly-admin/application.yml

COPY entrypoint.sh ./entrypoint.sh

RUN chmod +x entrypoint.sh

EXPOSE 8080

VOLUME /var/www/upload

ENTRYPOINT ["/var/www/entrypoint.sh"]