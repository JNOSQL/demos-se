# couchdb-demo

A JNoSQL Atemis project with Java SE using Document API with Couchdb as driver implementation.

![MongoDB Project](http://www.jnosql.org/img/logos/couchdb.png)


**Apache CouchDB** is open source database software that focuses on ease of use and having a scalable architecture. It has a document-oriented NoSQL database architecture and is implemented in the concurrency-oriented language Erlang; it uses JSON to store data, JavaScript as its query language using MapReduce, and HTTP for an API.


To run this project a MongoDB instance is required, so you can use either a local instalation or using Docker.


## Manual instalation

Follow the instructions in: https://docs.mongodb.com/manual/installation/


## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. Run MondoDB: verify MongoDB image name with the command `docker images`, it can be mongodb or mongo, and then execute this command `docker run -d --name mongodb-instance -p 27017:27017 mongodb`



## Run the code

With a MongoDB instance running go to the class **App** and have fun.
