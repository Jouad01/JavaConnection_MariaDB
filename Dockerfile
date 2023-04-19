FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /home/app

COPY src src
COPY pom.xml pom.xml
RUN mvn -f /home/app/pom.xml clean package -q

FROM openjdk:11.0-jre-slim-buster

LABEL "edu.daw.JavaConnection_MariaDB"="JavaConnection_MariaDB"
LABEL version=1.0-SNAPSHOT
LABEL mantainer="jouardiouardi@cifpfbmoll.eu"

RUN apt-get update && apt-get install -y mariadb-client

COPY --from=build /home/app/target/JavaConnection_MariaDB-1.0-SNAPSHOT.jar /usr/local/lib/JavaConnection_MariaDB.jar
COPY src/main/resources/hibernate.cfg.xml /usr/local/lib/hibernate.cfg.xml
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dhibernate.config=/usr/local/lib/hibernate.cfg.xml", "-jar", "/usr/local/lib/JavaConnection_MariaDB.jar"]

EXPOSE 3306