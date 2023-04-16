FROM openjdk:11

RUN apt update && \
	apt install maven

WORKDIR /usr/src/myapp

COPY ../hw12 /usr/src/myapp

RUN mvn clean install

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]