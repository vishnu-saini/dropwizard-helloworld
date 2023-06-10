FROM adoptopenjdk/openjdk11:latest

WORKDIR /service

EXPOSE 8080 8081

COPY target/helloworld-0.0.0.jar /service/bin/
COPY config.yml /service/conf/

ENTRYPOINT ["java", "-jar", "/service/bin/helloworld-0.0.0.jar", "server", "/service/conf/config.yml"]