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
The OAuth2 Service provides authentication and authorization functions for services in the `Bootiful Microservices` series that require security. To do this, the OAuth2 Service manages the `user` and `client` accounts. This means client applications and users who wish to access any protected resource must first be registered with the OAuth2 Service.

Any Service within the `Bootiful Microservices` series, with a protected resource can be thought of as a `Resource Server`.
The Service that does the authentication and authorization is the `Authorization Server`, hence this OAuth2 Service is the `Authorization Server` for all the services in the `Bootiful Microservices` series.

Note also that, in the OAuth2 Service, the `user account details` is a protected resource hence the OAuth2 Service can also be considered as a `Resource Server`. This makes the OAuth2 Service serve two roles, first as an `Authorization Server` and second as a `Resource Server`.

#### Real life example:
Lets imagine that in the `Bootiful Microservices` series, there is a service called `Income Service`. 
This income service does two things; 
1. allows a user to enter his income as is recieved. 
2. Displays a list of the users income as entered into the service.

Now first of all, to enter your income or view your income details, the `Income Service` requires you to be logged in or authenticated, but the `Income Service` does not handle user account creation or management.

So to do this in a microservices manner, the `Income Service` would have to delegate this activity to another service, and since our `Authorization Server` (a.k.a OAuth2 Service) does exactly this, all we need to do is configure the `Income Service` to communicate with the `Authorization Server` anytime anything concerning authentication and/or authorization comes its way.

In this way, when an anonymous user tries to enter his income, the `Income Service` will notice the user is not authenticated, and will redirect him to the `Authorization Server`. The user will then need to create an account with the `Authorization Server` if he doesn't have one yet, or just log in to his account on the `Authorization Server` if he already has one.

Once the user is logged in, the `Authorization Server` will redirect him back to the `Income Service`, but this time with other details allowing the `Income Service` to know that the user is authenticated.

The `Income Service` upon recieving the confirmation from the `Authorization Server` that the user is authenticated, will then grant access to the user to either enter his income or view his income details.

Imagine also again, that the user now wishes to have a summary of his income details, but unfortunately the `Income Service` doesn't offer this service.
Fortunately for him he finds a web application online that does exactly this. Lets assume this web application is callled `Income Summarizer`.

Now what you have to be aware of is that, this `Income Summarizer` is a third party application, it could as well be an independent service provided in the `Bootiful Microservices` series, just like the `Income Service`, it doesn't really matter, because the point is, between the `Authorization Server` and the `Income Service`, any other service/script/etc that wishes to interact with either of the two, is a third party service, period.

Now if the user wishes to run a summary on his income using the `Income Summarizer`, then since the data concerning his income is with the `Income Service`, his challenge will then be to get his data from the `Income Service` into the `Income Summarizer` so he can run his summary.

To achieve this, the `Income Summarizer` would have to be able to do some things on the user's behalf, for example: the `Income Summarizer` should be able to ask the `Income Service` for the income details of the user.

In the context of microservices - in this case the `Bootiful Microservices` series: 
* The `Income Summarizer` web application, is a `Client`(a third party application/script/etc)
* The `User` is a `Resource Owner`(owns his account details with the `Authorization Server` and his income details whth the `Income Service`)

Now under OAuth2 flow, with a `Resource Owner's` permission, a `Client` can request for a `Resource Owner's` protected resources on his behalf. For this to work, the `Client` must be registered with the `Authorization Server` as a so called third party application, in which case the `Authorization Server` will be aware of both the `User` and the `Client` who wishes to access the `User's` protected data.

Note that the permission to access a `User's` protected data can be revoked by the `Resource Owner` at any point in time through the `Authorization Server`.

This setup now makes it possible for the `Income Summarizer` to access the income data of the `Resource Owner` on the `Income Service` using an access token that was granted by the `Authorization Server` based on the `Resource Owner's` approval.

And these are mainly the services that this OAuth2 Service will be rendering to the other services in the `Bootiful Microservices` series.

### Running the Project (and its Dependencies)
* Run the [`Config-Server`](https://github.com/Prempeh-Gyan/1.0-Config-Server) to serve the property file for this OAuth2-Service
* Navigate into the source directory `cd 1.3-OAuth2-Service` and execute the following command: 
* `mvn spring-boot:run`
