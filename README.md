# Stars Group FeedMe Tech Test

## ## Created by Victor Stanculescu aka Syrius10

## Installation

### Prerequisite

* maven and java 1.8 are installed.

* It is assumed that the feed provider server is running as per the instructions given in the 'Getting Started' section.

Enter the below commands to build the application.
  ```
  mvn clean install
  ```

## Running

Enter the below command to run the application.
```
mvn spring-boot:run
```
Go to browser and enter http://localhost:8080

## Testing

There are 3 links on the web page.

* START Feed Process: Connects to provider server and starts receiving feeds.

* Show Persisted Fixtures: Shows Fixture JSON objects persisted in mongoDb so far.

* Abort Feed Process: Terminates the connection with provider server and returns Fixture JSON objects persisted in mongoDb till that time.


## Design considerations
* The project uses spring boot and embedded mongodb.

* In order to test the functionality a web page is created using thymeleaf template.

## Tasks

Basic and Intermediate Tasks have been implemented as following:

Basic Tasks

Create an app that connects the provider service on the exposed TCP port

Transform the proprietary data format into JSON using the field names and data types defined in the provider /types endpoint

Write unit tests

Intermediate Tasks

Save the JSON into a NoSQL store with a document per fixture. Each document should contain the event data and the child markets and outcomes for the fixture

## Getting Started

* Install Docker and Docker Compose: https://docs.docker.com/compose
* Start the mock data feed by typing `docker-compose up` in the root of the test directory
* Test mock feed API by opening a browser and navigating to http://localhost:8181/types
* Test mock feed by opening a new terminal and typing `telnet localhost 8282`. You should see a stream of packets.
* If the tests above succeed then you are ready to start coding. If you decide to attempt the Intermediate and Advanced tasks then we suggest adding to or using the services listed in the docker-compose.yml file
* To destroy the test environment you can type `docker-compose down`
