# micronaut-k8s

## The application

The service "finance-api" is a simple Micronaut-based microservice that exposes one endpoint: 
```curl http://localhost:8080/company/list``` which produces:

```json
[{"companyName":"Altavista","industry":"IT"},{"companyName":"PillPusher","industry":"Health"},{"companyName":"BrandTop","industry":"Marketing"}]
```

## Build Phase

We'll do two builds: one as a stand-alone JAR, and one as a GraalVM application

### Stand-alone JAR

* Build the artifact (JAR file): ```./gradlew assemble```
* Run the artifact: ```java -jar build/libs/finance-api-0.1-all.jar```

### GraalVM application

_Note_: Ensure you have docker installed and setup before proceeding

Build a GraalVM Native image containerised in Docker

* Build the artifact (docker image): ```./gradlew dockerBuildNative```
* Run the artifact: ```docker container run --name finance-api finance-api```
* Obtain the IP address of the running container: ```docker container inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' finance-api```

#### A few numbers:

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

## Deploy phase

Deploying on k8s/minikube

_Note_: Ensure you have docker installed and setup before proceeding

1. ```minikube start```
2. ```eval $(minikube docker-env)```
3. cd into "finance-api" subfolder and then: ```./gradlew dockerBuildNative```
4. confirm image creation ```docker image ls```
5. cd into "k8s" subfolder and then: