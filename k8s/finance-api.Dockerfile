FROM openjdk:11-slim
COPY . /opt/finance-api
WORKDIR /opt/finance-api
CMD ["java", "-jar", "finance-api-0.1-all.jar"]