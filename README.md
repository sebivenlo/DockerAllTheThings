# DockerAllTheThings
An introduction to docker


##what does it do ?
...
###container vs image
...
##Setup

Linux: `wget -qO- https://get.docker.com/ | sh`
Mac OSX:  [**do a bunch of Stuff**](http://docs.docker.com/mac/started/)
Windows: **[do a bunch of more Stuff](http://docs.docker.com/windows/started/)**



## Use case
- we have a server application which runs java and the [Spring framework](http://spring.io/)
- we develop locally and want to run the application with docker on a server


##getting started
### Dockerfile
Docker uses Dockerfiles to build containers.
our Docker file looks like this:

    FROM ubuntu:14.04
	
	MAINTAINER Borian Brückner
	
	RUN apt-get update
	
	RUN apt-get install default-jre -y
	RUN apt-get install default-jdk -y
	RUN apt-get install maven -y
	
	ADD pom.xml /app/
	ADD src/ /app/src/
	
	WORKDIR /app/
	
	RUN mvn package
	
	EXPOSE  8080
	
	CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/spring-boot-sample-tomcat-1.1.5.RELEASE.jar"]

###  Syntax
- FROM
- RUN
- ADD
- WORKDIR
- EXPOSE
- CMD

###Commands
- `docker build .`
- `docker ps -l` list all containers
-`docker run -i -t f0cd4ff20f52` starts a container with the given id

- `docker tag d583c3ac45fd springDemo` gives the container a name so we can use it more easily
- `docker images`  lists all images you created with docker

