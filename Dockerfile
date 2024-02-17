FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app


# Copy the source code
COPY . .
# Download MySQL Connector JAR
RUN curl -o mysql-connector-java-8.0.17.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.17/mysql-connector-java-8.0.17.jar

# Build the project
RUN mvn compile package

# Continue with the rest of your Dockerfile
FROM quay.io/wildfly/wildfly:26.1.3.Final-jdk11 AS deploy

RUN rm /opt/jboss/wildfly/standalone/configuration/standalone.xml

COPY --from=build /app/target/attendance.war /opt/jboss/wildfly/standalone/deployments/
COPY --from=build /app/standalone.xml /opt/jboss/wildfly/standalone/configuration/

RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/mysql-connector-java-8.0.17.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/


FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
LABEL authors=" "


WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests -X


FROM quay.io/wildfly/wildfly:26.1.3.Final-jdk17 AS deploy

RUN rm /opt/jboss/wildfly/standalone/configuration/standalone.xml

COPY --from=build /app/target/assetmanager.war /opt/jboss/wildfly/standalone/deployments/
COPY --from=build /app/standalone.xml /opt/jboss/wildfly/standalone/configuration/


RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/mysql-connector-j-8.2.0.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/

# Copy the entrypoint script
COPY sqldumps.sql /opt/jboss/wildfly/bin/
COPY entrypoint.sh /opt/jboss/wildfly/bin/
USER root
RUN chown root:root /opt/jboss/wildfly/bin/entrypoint.sh
RUN chmod +x /opt/jboss/wildfly/bin/entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/opt/jboss/wildfly/bin/entrypoint.sh"]

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
