FROM openjdk:21
WORKDIR /app
COPY build/libs/product-service-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]