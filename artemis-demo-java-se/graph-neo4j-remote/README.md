# Graph-Neo4J

A JNoSQL Artemis project with Java SE using using Graph API with Neo4J.

![Neo4J Project](http://www.jnosql.org/img/logos/neo4j.png)

Neo4j is a graph database management system developed by  Neo4j, Inc. Described by its developers as an ACID-compliant transactional database with native graph storage and processing, Neo4j is the most popular graph database according to db-engines.com. Neo4j is available in a GPL3-licensed open-source "community edition", with online backup and high availability extensions licensed under the terms of the Affero General Public License. Neo also licenses Neo4j with these extensions under closed-source commercial terms. Neo4j is implemented in Java and accessible from software written in other languages using the Cypher Query Language through a transactional HTTP endpoint, or through the binary 'bolt' protocol.


To run this project a nEO4j instance is required, so you can use either a local instalation or using Docker.


## Manual instalation

Follow the instructions in: https://neo4j.com/docs/operations-manual/current/installation/


## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/neo4j
1. Run docker command
1. `docker run --publish=7474:7474 --publish=7687:7687 --volume=$HOME/neo4j/data:/data neo4j`


## Check the configuration

Check the configuration in GraphProducer.

This class has three attributes:

* `private static final String URL = "bolt://localhost:7687";`
* `private static final String USER = "neo4j";`
* `private static final String PASSWORD = "admin";`

## Run the code

### BookApp

Library recommendation, category based that shows the software categories, the software books and also a book those is
 Software and Java.

![BookApp](Book.png)

### MarketingApp


The marketing campaign that needs to match from some rules relationship based with four people.

![MarketingApp](Marketing.png)

### TravelApp

Given cities and travelers, this TravelApp will return the most famous city, the person who most travel and also some
 friends suggestion placed by the visited cities.

![Travel](Travel.png)
