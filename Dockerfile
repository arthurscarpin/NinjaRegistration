FROM amazoncorretto:17
LABEL authors="scarpinarthur.dev@gmail.com"
WORKDIR /app
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh
COPY target/NinjaRegistration-0.0.1-SNAPSHOT.jar /app/ninja-registration.jar
ENTRYPOINT ["java","-jar","ninja-registration.jar"]