# Stage 1: Build stage
FROM maven:3.6.0-jdk-13-alpine AS build

WORKDIR /usr/src/tatu

COPY . .

RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests

# Remove Maven and its dependencies
RUN apk --no-cache del maven

# Stage 2: Deployment stage
FROM jboss/base-jdk:11

WORKDIR /opt/jboss/wildfly/standalone/deployments/

COPY --from=build /usr/src/tatu/target/attendance.war .

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]


# alpine, slim
# FROM Openjdk as build
# # WORKDIR /tatu
# COPY . .
# RUN maven ----


# Multi-stage building

