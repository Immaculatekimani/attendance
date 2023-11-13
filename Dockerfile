# Use the official WildFly image as the base image
FROM jboss/wildfly:latest

# Copy your Java web application WAR file to the deployments directory of WildFly
COPY target/attendance.war /opt/jboss/wildfly/standalone/deployments/

# Expose the ports used by WildFly (8080 for HTTP, 9990 for the management console)
EXPOSE 8080 9990

# Start WildFly in standalone mode
CMD ["sudo", "/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
