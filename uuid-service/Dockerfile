FROM openjdk:17-alpine

RUN apk --no-cache add curl

ADD ./build/libs/*.jar /uuid-service-kubernetes.jar

EXPOSE 9200

ENTRYPOINT java -jar uuid-service-kubernetes.jar