FROM openjdk:17-alpine

RUN apk --no-cache add curl

COPY kubernetes-liveness.txt kubernetes-liveness.txt

ENV UUID-SERVICE-HOST='http://study-kubernetes-node-port:9200'
ENV DATABASE-HOST=study-kubernetes-postgres-external
ENV DATABASE-PORT=5432
ENV DATABASE-USER=background-app-user
ENV DATABASE-PASSWORD=123@qwe#J

ADD ./build/libs/*.jar /background-kubernetes-api.jar

EXPOSE 9000

ENTRYPOINT java -jar background-kubernetes-api.jar