# mongodb-demo

A JNoSQL Atemis project with Java SE using Document API with MongoDB as driver implementation.

![MongoDB Project](http://www.jnosql.org/img/logos/mongodb.png)


**Mongodb**: MongoDB is a free and open-source cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.


To run this project a MongoDB instance is required, so you can use either a local instalation or using Docker.


## Manual instalation

Follow the instructions in: https://docs.mongodb.com/manual/installation/


## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. Run MongoDB: verify MongoDB image name with the command `docker images`, it can be mongodb or mongo, and then execute this command `docker run -d --name mongodb-instance -p 27017:27017 mongo`



## Run the code

With a MongoDB instance running go to the class **App** and have fun.
