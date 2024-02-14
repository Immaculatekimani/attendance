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

# Remove any existing attendance.war file
RUN rm -rf /opt/jboss/wildfly/standalone/deployments/attendance.war

# Copy the new attendance.war file
COPY --from=build /app/target/attendance.war /opt/jboss/wildfly/standalone/deployments/

# Copy the customized standalone.xml file
COPY --from=build /app/standalone.xml /opt/jboss/wildfly/standalone/configuration/

# Create the necessary directory structure for MySQL Connector JAR
RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/

# Copy the module.xml and MySQL Connector JAR
COPY --from=build /app/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
COPY --from=build /app/mysql-connector-java-8.0.17.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
