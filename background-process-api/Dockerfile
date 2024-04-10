FROM openjdk:17-alpine

RUN apk --no-cache add curl

COPY kubernetes-liveness.txt kubernetes-liveness.txt
ADD ./build/libs/*.jar /background-kubernetes-api.jar

EXPOSE 9000

ENTRYPOINT java -jar background-kubernetes-api.jar