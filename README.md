# Spring Bootiful Microservices

#### Navigation Guide: [`Previous Repo: Discovery-Service`](https://github.com/Prempeh-Gyan/1.2-Discovery-Service)   |   [`Next Repo: Gateway-Service`](https://github.com/Prempeh-Gyan/1.4-Gateway-Service)

### Travis
[![Build Status](https://travis-ci.org/Prempeh-Gyan/1.3-OAuth2-Service.svg)](https://travis-ci.org/Prempeh-Gyan/1.3-OAuth2-Service)

### Heroku
To deploy this project to Heroku, update the [`configuration file`](https://github.com/Prempeh-Gyan/1.1-Config-Repo/blob/master/services/OAuth2-Service/oauth2-service.yml ) to point to the Heroku locations of the dependent services before deploying this to Heroku.

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy?template=https://github.com/Prempeh-Gyan/1.3-OAuth2-Service)

### Getting Started
*Required*
* [`Maven`](https://maven.apache.org/) 3.3+
* [`JDK`](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 8+
Get the project from the source repository
>`git clone https://github.com/Prempeh-Gyan/1.3-OAuth2-Service.git`

### About This Service
This Gateway-Service uses the Netflix Zuul project to provide service routing capabilities for all the microservices in the `Bootiful Microservices`.

This means that, the Gateway-Service proxies service requests coming from the "outside world" to any of the `Bootiful Microservices`.

With this Gateway-Service, it is ensured that all calls to any of the microservices go through a single "front door"(this Gateway-Service) before the actual targeted service is invoked.

With this architecture, it is very easy to address cross-cutting concerns such as security and logging among others by just using the Gateway-Service as a centralized Policy Enforcement Point(PEP). 
 
This Service also integrates with Kafka to send messages to other microservices when certain events occur.

### Running the Project (and its Dependencies)
* Set up and run [`kafka`](https://kafka.apache.org/) then update the [`configuration file`](https://github.com/Prempeh-Gyan/1.1-Config-Repo/blob/master/services/Gateway-Service/gateway-service.yml ) to point to it
* Run the [`Config-Server`](https://github.com/Prempeh-Gyan/1.0-Config-Server) to serve the property file for this Gateway-Service
* The Gateway-Service needs the [`Discovery-Service`](https://github.com/Prempeh-Gyan/1.2-Discovery-Service) to know the locations of all the other microservices in order to route incoming service requests to intended targeted services
* Some resources in the Gateway-Service are protected with Oauth2 security, hence the [`OAuth2-Service`](https://github.com/Prempeh-Gyan/1.3-OAuth2-Service)  needs to be set up as well.
