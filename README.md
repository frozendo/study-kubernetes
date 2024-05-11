# Study Kubernetes

This repository has some examples of **Kubernetes** and **Kubernetes Resources**.
The goals were to build a simple solution, to help get a few examples of how to deploy an application, or some applications, on Kubernetes infrastructure.

### Database

The database used in this example is simple. We only have one table, responsible for storing a history of the process executed by the application.

This database runs outside Kubernetes, and we use a [docker-compose](https://github.com/frozendo/study-kubernetes/blob/main/docker-compose.yml) file to configure how this database should work. On the container's startup, we execute two scripts. The first creates the table background_entity, which will store our data. The second one makes the application user and applies some grants for this user. We configure a [headless service without selectors](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/postgres/postgres-external-service.yaml) to expose the database inside the cluster and create an [endpoint](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/postgres/postgres-external-endpoint.yaml) pointing to the database IP address.

### UUID Service

This application only generates the IDs that will be used as an identifier on the database. The goal is to show how the communication between applications inside Kubernetes works.

The API receives a request on `/generate` endpoint, and returns a new UUID.

The infrastructure of this application is composed of: 
- [deployment.yml](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/uuid-service/deployment.yaml): configures our pods. As the app is simple, we run only one replica.
- [nodePort.yml](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/uuid-service/nodePort.yaml): configure a service of type nodePort.
- 
Using a nodePort service, in addition to being possible for other applications inside the cluster to consume this service using the internal _clusterIp_, it is also possible to call it from outside the cluster. For this, we can call any IP of the cluster and use port 32000.
Background API


### Background API

This API is our principal application. It works simply: the API receives a 
number and executes a background process (creating a new thread for each processing). The application will count until the goal received in the request is reached.

The application saves the data of each process in the bank, storing the current status (Scheduled, Running, Finished, or Error), how many counts have been made, and also how many counts it should do.

The identifier for each processing is obtained from the UUID Service, making a rest call for this service.

The infrastructure of this application is composed of: 
- [deployment.yml](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/background-api/deployment.yaml): configures our pods. This app starts with two replicas.
- [clusterIp.yml](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/background-api/clusterIp.yaml): configure a service for the application that will redirect and balance requests to our pods.
- [ingress.yml](https://github.com/frozendo/study-kubernetes/blob/main/kubernetes/background-api/clusterIp.yaml): enable the application to receive requests from outside the cluster.

##### Configure Ingress: 
We need to create a host for ingress to work correctly. To do this, first get the IP address of the main container (control plane) of the cluster run by kind:
```
docker inspect study-kubernetes-cluster-control-plane | grep IPAddress
```

Now, open the `/etc/hosts` file and add the line below:
```
<ip-control-plane> backgroundprocess.io
```

This configures our control plane IP to respond to the backgroundprocess.io host.
With this done, we can make calls to our application within the cluster

```
curl -X GET 'http://backgroundprocess.io/hello'
curl -X POST 'http://backgroundprocess.io/background/10'
curl -X GET 'http://backgroundprocess.io/background'
```
