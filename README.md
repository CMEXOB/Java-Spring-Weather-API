# Java Spring Weather API

Java Spring Weather API - System for collecting and processing weather data.

The system consists of two parts:
-  Weather Server
-  Weather Sensor

Server - RESTful service for filtering and sorting weather data. Provides the ability to register a sensor, send information about all active sensors, save received weather measurements data and send weather data from either one or all sensors. When an error occurs, the server sends special messages.

Sensor - service for collection weather data and sent to Server. When launched, it registers with the server and sends weather measurements data every 3 - 15 seconds.

## Dependencies

Server uses PostgreSQL databases so it must be installed for the system to function.

All other project dependencies exist in pom.xml file and once your run the project, all dependencies will be downloaded.


## Run Locally

Clone the project

```bash
  git clone https://github.com/CMEXOB/Java-Spring-Weather-API.git
```
Before start Server and Sensor you need change their application.properties files

Server application.properties (WeatherServer/src/main/resources/application.properties):

-	Change datasourse url, username and password
-	(Optional) Change server port (default=8080)

Sensor application.properties (WeatherSensor/src/main/resources/application.properties):
-	Change Server address
-	Change Sensor name
-	(Optional) Change sensor port(default=8089)

Start Weather Server.

```bash
   mvn  -f ./WeatherServer/pom.xml spring-boot:run
```
Start Weather Sensor. 

```bash
   mvn  -f ./WeatherSensor/pom.xml spring-boot:run
```


## API Reference

Server has Swagger API documentation. To view it go to: 

```
  {server.ip}/swagger-ui/index.html
```

## Development time


|Planning| Development| Testing| Documentation|
|:------ | :--------- | :----- | :----------- |
|3 hour  | 18 hour    | 10 hour| 2 hour       |

