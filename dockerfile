FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/Tafbookingms.jar bookingms.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookingms.jar"]