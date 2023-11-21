# Stage 1: Build stage
FROM maven:3.6.0-jdk-13-alpine AS build

# Set the working directory inside the container
WORKDIR /tatu

# Copy the contents of the current directory to the working directory
COPY . .

# Build the Maven project, skipping tests
RUN mvn clean install -DskipTests -X

# Remove Maven and its dependencies to reduce image size
RUN apk --no-cache del maven

# Stage 2: WildFly stage
FROM jboss/wildfly:latest AS deploy

# Copy the built WAR file from the build stage to the WildFly deployment directory
COPY --from=build /tatu/target/attendance.war /opt/jboss/wildfly/standalone/deployments/

# Expose port 8080 for the application
EXPOSE 8080

# Start WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]