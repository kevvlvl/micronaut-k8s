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

* Build the artifact: ```./gradlew assemble```
* Run the artifact: ```java -jar build/libs/finance-api-0.1-all.jar```

### GraalVM application

