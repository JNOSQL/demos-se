# ArangoDB-demo-graph

![ArangoDB Project](http://www.jnosql.org/img/logos/ArangoDB.png)

A JNoSQL Artemis project with Java SE using Document API with ArangoDB as driver implementation.


**ArangoDB**: ArangoDB is a native multi-model database system[1] developed by triAGENS GmbH. The database system supports three important data models (key/value, documents, graphs) with one database core and a unified query language AQL (ArangoDB Query Language). The query language is declarative and allows the combination of different data access patterns in a single query. ArangoDB is a NoSQL database system but AQL is similar in many ways to SQL.
              


### How To test

Once this a communication layer to ArangoDB, we're using integration test, so you need to install ArangoDB. The recommended way is using Docker.

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://hub.docker.com/r/couchbase/server/
1. Run docker command
1. `docker run -e ARANGO_NO_AUTH=1 -d --name arangodb-instance -p 8529:8529 -d arangodb/arangodb`
1. Execute the test `mvn clean install`
