FROM java:8

MAINTAINER Borian Brückner

ADD spring-boot-todomvc/ /app

WORKDIR /app/

RUN ./gradlew build

EXPOSE  8080

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/spring-boot-todomvc.jar"]
