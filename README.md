# micronaut-k8s

## The application

The service "finance-api" is a simple Micronaut-based microservice that exposes one endpoint: 
```curl http://localhost:8080/company/list``` which produces:

```json
[{"companyName":"Altavista","industry":"IT"},{"companyName":"PillPusher","industry":"Health"},{"companyName":"BrandTop","industry":"Marketing"}]
```

## Build

We'll do two builds: one as a stand-alone JAR, and one as a GraalVM application

### Stand-alone JAR

* Build the artifact (JAR file): ```./gradlew build```
* Run the artifact: ```java -jar build/libs/finance-api-0.1-all.jar```

## Deploy on k8s

For the purpose of this exercise I'm using minikube. Ensure it's setup properly and run these commands:

1. ```minikube start```
1. ```eval $(minikube docker-env)```

### Deploy Jaeger

First, we need to deploy Jaeger for distributed tracing. It will be deployed in its own namespace separate from our API.

1. Create the ctrl namespace ```kubectl create -f control-ns.json```
1. Create the jaeger deployment ```kubectl create -f jaeger-deploy.yml```
1. Create the jaeger deployment ```kubectl create -f jaeger-svc.yml```
1. List all minikube services: ```minikube service list```
1. Browse the Jaeger URL. In my case: ```http://192.168.99.103:32315```

### Deploy the audit-api using the OpenJDK image

1. From the audit-api dir: ```./gradlew build```
1. From the audit-api/build/libs folder: ```docker image build -f ../../../k8s/audit-api.Dockerfile  -t audit-api-openjdk:1.0 .```
1. From the k8s dir: ```kubectl apply -f audit-api-deploy.yml```, ```kubectl apply -f audit-api-svc.yml```

### Deploy the finance-api JAR using the OpenJDK image

1. From the finance-api dir: ```./gradlew build```
1. From the finance-api/build/libs folder: ```docker image build -f ../../../k8s/finance-api.Dockerfile  -t finance-api-openjdk:1.0 .```
1. From the k8s dir: ```kubectl apply -f finance-api-deploy.yml```, ```kubectl apply -f finance-api-svc.yml```
1. Then, ```minikube service list``` to find the URL. In my case: ```curl http://192.168.99.103:32370/company/list```
1. Open Jaeger (find its url ), and you will find the traces for each call, to the finance API and then to the audit API

### Deploy the JAR as a GraalVM native image

Build a GraalVM Native image containerised in Docker

The only step to be performed here is to use gradle to build a GraalVM native image using this command:

```./gradlew dockerBuildNative```

The result is a docker image (```docker image ls```) which we can then refer in a k8s deployment manifest, or run using docker run.

## Benchmark JAR vs GraalVM image

Running the Jar:
```shell
➜  libs git:(main) ✗ java -jar finance-api-0.1-all.jar
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
Micronaut (v2.5.5)

23:39:27.528 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 718ms. 
```

Running the Docker native image:
```shell
➜  finance-api git:(main) ✗ docker container run finance-api --name finance-api
/app/application: /usr/lib/libstdc++.so.6: no version information available (required by /app/application)
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_ 
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_ 
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
  Micronaut (v2.5.5)

03:32:22.096 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 89ms. 
```

As we can see, we dropped start-up time for a simple app from 718ms to 89ms!
