# Getting Started

## Prerequisites
* JDK 17
* docker & docker-compose

## How to:

### Run the app
* Fill in the clientId and clientSecret in:
    * application-docker.yml
    * This can be shared via email or you could register a new OAuth app here: https://github.com/settings/developers
* Run ```docker compose up```
* Go to http://localhost:8080/swagger-ui.html
* You will be redirected to authorize yourself with GitHub
* Try out the API on the Swagger UI

### Debug the app
* Fill in the clientId and clientSecret in:
  * application.yml
  * This can be shared via email or you could register a new OAuth app here: https://github.com/settings/developers
* Run ```docker compose -f docker-compose-deps.yml up```
* Start the IntelliJ debugger for `ToDoApiApplication.java`
  * Set up the breakpoints
* Go to http://localhost:8080/swagger-ui.html
* You will be redirected to authorize yourself with GitHub
* Try out the API on the Swagger UI