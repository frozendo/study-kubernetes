# Start postgres database
echo "Start postgress database on docker container"
docker-compose -f docker-compose.yml up -d 

# Start kind cluster
echo "Start cluster kind-study-kubernetes-cluster and config as current cluster"
kind create cluster --name study-kubernetes-cluster --config kubernetes/general/kind-config.yaml

kubectl config use-context kind-study-kubernetes-cluster

# Create study lab namespace
echo "Create namespace kubernetes lab and define as current namespace"
kubectl apply -f kubernetes/general/namespace.yaml

kubectl config set-context --current --namespace=kubernetes-lab

# config service for postgres database
echo "Config postgres database service"
kubectl apply -f kubernetes/postgres/postgres-external-service.yaml
kubectl apply -f kubernetes/postgres/postgres-external-endpoint.yaml

# background-process-api application
echo "Build background-process-api"

cd background-process-api 

./gradlew clean build -x test

docker build -t background-run-api:study .

kind load docker-image background-run-api:study --name study-kubernetes-cluster

cd ..

echo "Create resources for background-process-api"

kubectl apply -f kubernetes/background-api/deployment.yaml

kubectl apply -f kubernetes/background-api/clusterIp.yaml


# uuid-service application
echo "Build uuid-service"

cd uuid-service 

./gradlew clean build -x test

docker build -t uuid-service:study .

kind load docker-image uuid-service:study --name study-kubernetes-cluster

cd ..

echo "Create resources for uuid-service"

kubectl apply -f kubernetes/uuid-service/deployment.yaml

kubectl apply -f kubernetes/uuid-service/nodePort.yaml