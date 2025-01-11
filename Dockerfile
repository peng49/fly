FROM maven:3.8-openjdk-17 as builder

WORKDIR /usr/src/mymaven

COPY . /usr/src/mymaven

RUN apt update && apt install nodejs npm -y && npm -version && \
    cd fly-admin/src/main/resources/vue && npm install && npm run build:prod && \
    cd /usr/src/mymaven && mvn clean && mvn package -am -amd -pl fly-web -pl fly-admin -DskipTests=true

FROM openjdk:17.0

WORKDIR /var/www/

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=fly
ENV DB_USERNAME=root
ENV DB_PASSWORD=password

COPY --from=builder /usr/src/mymaven/fly-web/target/fly-web-2.0.0.jar /var/www/fly-web/application.jar
COPY --from=builder /usr/src/mymaven/fly-web/src/main/resources/application-prod.yml /var/www/fly-web/application.yml

COPY --from=builder /usr/src/mymaven/fly-admin/target/fly-admin-2.0.0.jar /var/www/fly-admin/application.jar
COPY --from=builder /usr/src/mymaven/fly-admin/src/main/resources/application-prod.yml /var/www/fly-admin/application.yml

COPY entrypoint.sh ./entrypoint.sh

RUN chmod +x entrypoint.sh

EXPOSE 8080

VOLUME /var/www/upload

ENTRYPOINT ["/var/www/entrypoint.sh"]