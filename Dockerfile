FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /home/app
COPY . .
RUN mvn -f /home/app/pom.xml clean package -q

FROM openjdk:11-jre-slim

RUN apt-get update && apt-get install -y mariadb-client

COPY --from=build /home/app/target/JavaConnection_MariaDB-1.0-SNAPSHOT.jar /usr/local/lib/JavaConnection_MariaDB.jar
COPY src/main/resources/META-INF/persistence.xml /usr/local/lib/persistence.xml
ENV APP_VERSION=1.0-SNAPSHOT

LABEL "edu.daw.JavaConnection_MariaDB"="JavaConnection_MariaDB" \
      version=$APP_VERSION \
      maintainer="jouardiouardi@cifpfbmoll.eu"
EXPOSE 8080
