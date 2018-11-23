FROM openjdk:11-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/orders-api-1.0-SNAPSHOT.jar /app

EXPOSE 8082

CMD java -jar orders-api-1.0-SNAPSHOT.jar