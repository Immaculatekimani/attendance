FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app


# Copy the source code
COPY . .

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

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
