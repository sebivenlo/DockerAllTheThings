FROM java:8

MAINTAINER Borian Brückner

ADD spring-boot-todomvc/ /app

WORKDIR /app/

ENV GRADLE_USER_HOME=gradle

#build & delete everything other than build result
RUN ./gradlew build \
    &&  find . -type f -not -name 'spring-boot-todomvc.jar' | xargs rm  \
    &&  find . -type d -empty -delete # delete empty folders

EXPOSE  8080

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/spring-boot-todomvc.jar"]
