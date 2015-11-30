# DockerAllTheThings
An introduction to docker

##why?
|  advantages: | |
|---|---|
| isolation  |  no interference |
| layers | images can share parts with each other
| fast  |  docker startup: **2sec** virtualbox startup: **35sec** |
|  dependencies | easy to have multiple versions of same library |
| versioning | easy tracking of configuration and dependencies
| production | local development very close to production


---

##what?
**aufs**
union file system
**image**
read-only template, ex. Ubuntu + Apache + Java + Application
**container**
 Each container is created from a Docker image.
 Docker containers can be run, started, stopped, moved, and deleted.
 **docker daemon**
  [LXC](http://en.wikipedia.org/wiki/Lxc) -> [runc](https://github.com/opencontainers/runc)

---

##Setup

Linux: `wget -qO- https://get.docker.com/ | sh`
Mac OSX:  [**do a bunch of Stuff**](http://docs.docker.com/mac/started/)
Windows: **[do a bunch of more Stuff](http://docs.docker.com/windows/started/)**

---

##getting started
### Dockerfile
Docker uses Dockerfiles to build containers.
Every command in the Dockerfile creates a new layer in the filesystem.

our Docker file looks like this:

    FROM java:8

	MAINTAINER Borian Brückner

	ADD spring-boot-todomvc/ /app

	WORKDIR /app/

	RUN ./gradlew build

	EXPOSE  8080

	CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","build/libs/spring-boot-todomvc.jar"]

---

#### Syntax
- `FROM` - Base image which you build upon (ex. [java](https://github.com/docker-library/java/blob/200ecf22e5a23cb48cbb3ce47aa08aa3b49a0d2d/openjdk-8-jdk/Dockerfile), [ubuntu](https://github.com/tianon/docker-brew-ubuntu-core/blob/6dba3ee12ff996640d1043139d5abf8c744862e2/trusty/Dockerfile)
- `RUN` - runs a command inside the container
- `ADD` - copies a file/folder into the container, **and** can download from URL's or unpack archives
- `WORKDIR` - set working directory for other commands `RUN`, `CMD` etc.
- `EXPOSE` - marks a port to be used outside the container, **without** `-p` flag only exposes to other docker containers
- `CMD` - default command to run when you start a container


---

###Build & run
- `docker build -t app-demo .`  - builds an image from a given Dockerfile
- `docker images` - lists all images you created with docker
- `docker run -i -p 8080:8080 -t app-demo` - starts a container with the given id or tag
- `docker ps -l` - list all containers

to connect multiple containers you could run
`docker run -d -p 8080:8080 --name app --env spring.profiles.active=production --link db -t app-demo`
**or** use `docker-compose`


---

### Docker-compose
fast way to run existing images 	

	 app:
	  image: java:8
	  environment:
	    GRADLE_USER_HOME: /app/.gradle
	    spring.profiles.active: production
	  ports:
	    - "8080:8080"
	  volumes:
	    - spring-boot-todomvc:/app
	  links:
	    - db
	  working_dir: "/app"
	  command: "bash ./gradlew bootRun"

	db:
	  image: postgres:latest
	  environment:
	    POSTGRES_USER: postgres
	    POSTGRES_PASSWORD: postgres
	    POSTGRES_DB: testdb
	  ports:
	    - "localhost:5432:5432"
	  expose:
	    - 5432

---
**run**
`docker-compose up -d` start all services in daemon mode
`docker-compose run --service-ports db`
`docker-compose run --service-ports app`

**easy  scaling**
` docker-compose scale web=2 worker=3`

more scaling ....

### docker swarm

[video](https://embed-ssl.wistia.com/deliveries/2a0f6a5e1b77423d85b501aea668b664d4609b1f/file.mp4)

[info](https://www.docker.com/docker-swarm)

---

### pro  +
+ build once, run anywhere
+ easy dependencies
+ compatibility

### con -
- Increased complexity
- logging and metrics
- security ([more](http://reventlov.com/advisories/using-the-docker-command-to-root-the-host) [info](http://www.projectatomic.io/blog/2014/08/is-it-safe-a-look-at-docker-and-security-from-linuxcon/))

---

### cleanup after testing !
- delete all containers `docker rm $(docker ps -a -q)`
- delete all images `docker rmi $(docker images -q)`




### resources

[docker presentation](http://slides.com/kennycoleman/introdocker#/)

[dokku deploy tutorial](http://progrium.viewdocs.io/dokku/application-deployment/) ([more](https://www.digitalocean.com/community/tags/dokku))

[docker-compose load balancing & scaling](http://eyenx.ch/2015/04/18/loadbalancing-containers-with-docker-compose/)

[jenkins inside docker](https://engineering.riotgames.com/news/putting-jenkins-docker-container)

[ansible docker provision](http://blog.mist.io/post/82383668190/move-fast-and-dont-break-things-testing-with)

[run docker inside docker inside ...](https://blog.docker.com/2013/09/docker-can-now-run-within-docker/)

[manage docker with minecraft](https://github.com/docker/dockercraft)

[GUI Applications inside docker](http://fabiorehm.com/blog/2014/09/11/running-gui-apps-with-docker/)

[docker without docker](https://chimeracoder.github.io/docker-without-docker/#35)
