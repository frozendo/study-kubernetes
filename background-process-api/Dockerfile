FROM openjdk:17-alpine

RUN apk --no-cache add curl

COPY kubernetes-liveness.txt kubernetes-liveness.txt

ENV UUID-SERVICE-HOST='http://uuid-service:9200'
ENV DATABASE-HOST=kubernetes-database
ENV DATABASE-PORT=5432

ADD ./build/libs/*.jar /background-kubernetes-api.jar

EXPOSE 9000

ENTRYPOINT java -jar background-kubernetes-api.jar