FROM maven:3.8.7-eclipse-temurin-17-alpine AS build
RUN apk add git
RUN git clone https://github.com/KryniuPL/scoring-data-generator-server
WORKDIR "/scoring-data-generator-server"
RUN mvn install
WORKDIR target
EXPOSE 8090
ENTRYPOINT ["java","-jar","scoringdatagenerator-1.0.0.jar"]
