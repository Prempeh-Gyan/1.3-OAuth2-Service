# Spring Bootiful Microservices (OAuth2-Service)

#### Navigation Guide: [`Previous Repo: Discovery-Service`](https://github.com/Prempeh-Gyan/1.2-Discovery-Service)   |   [`Next Repo: Gateway-Service`](https://github.com/Prempeh-Gyan/1.4-Gateway-Service)

### Travis
[![Build Status](https://travis-ci.org/Prempeh-Gyan/1.3-OAuth2-Service.svg?branch=master)](https://travis-ci.org/Prempeh-Gyan/1.3-OAuth2-Service)

### Heroku
To deploy this project to Heroku, update the [`configuration file`](https://github.com/Prempeh-Gyan/1.1-Config-Repo/blob/master/services/OAuth2-Service/oauth2-service.yml) to point to the Heroku locations of the dependent services before deploying this to Heroku.

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy?template=https://github.com/Prempeh-Gyan/1.3-OAuth2-Service)

### Getting Started
Required
* [`Maven`](https://maven.apache.org/) 3.3+
* [`JDK`](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 8+
Get the project from the source repository
>`git clone https://github.com/Prempeh-Gyan/1.3-OAuth2-Service.git`

### About This Service
The OAuth2 Service provides authentication and authorization functionalities for services in the `Bootiful Microservices` series that require security.

Any Service with a protected resource is also referred to as a `Resource Server`. 
In this `Bootiful Microservices` series, all `Resource Servers` are configured to point to the `OAuth2 Service` also known as the `Authorization Server` for authentication and authorization.

To access a protected resource:
* A client must first be registered with the Authorization Server(client == Web App || Http client).
* The owner of the resource the client wants to access must also be registered with the Authorization Server(Resource Owner = Application User).
* Authenticate itself with the Authorization Server(OAuth2 Service), to aquire an access token
* With the access token, the client can now request for the protected resource passing the access token to the Resource Server.
* The Resource Server will then communicate with the Authorization Server to validate the access token before allowing access to the protected resource.

### Running the Project (and its Dependencies)
* Run the [`Config-Server`](https://github.com/Prempeh-Gyan/1.0-Config-Server) to serve the property file for this Discovery-Service
* Navigate into the source directory `cd 1.2-Discovery-Service` and execute the following command: 
* `mvn spring-boot:run`
