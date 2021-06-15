FROM openjdk:11-slim
COPY . /opt/audit-api
WORKDIR /opt/audit-api
CMD ["java", "-jar", "audit-api-0.1-all.jar"]