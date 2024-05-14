FROM openjdk:21-jdk-slim

MAINTAINER 0x399

COPY target/restaurant-0.0.1-SNAPSHOT.jar restaurant-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "restaurant-0.0.1-SNAPSHOT.jar"]