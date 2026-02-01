FROM openjdk:17-jdk-alpine
COPY target/inditexecommerceservice-0.0.1-SNAPSHOT.jar service-app.jar
EXPOSE 8080
CMD ["java", "-jar", "service-app.jar"]