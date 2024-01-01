FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/fashion-0.0.1-SNAPSHOT.jar /app/fashion.jar
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]