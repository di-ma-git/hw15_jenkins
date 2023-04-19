FROM maven:3.8.5-openjdk-17

#RUN apt-get update

#ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

WORKDIR /usr/src/myapp

COPY ./ /usr/src/myapp

RUN mvn clean package -DskipTests

ENTRYPOINT ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]
#CMD /bin/bash 