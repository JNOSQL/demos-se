# document-double

A JNoSQL Atemis project with Java SE using Document API with MongoDB and Couchbase as driver implementation.

![MongoDB Project](http://www.jnosql.org/img/logos/mongodb.png)


**Mongodb**: MongoDB is a free and open-source cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.

![Couchbase Project](http://www.jnosql.org/img/logos/couchbase.svg)


**Couchbase**: Couchbase Server, originally known as Membase, is an open-source, distributed multi-model NoSQL document-oriented database software package that is optimized for interactive applications.



To run this project a MongoDB and Couchbase instance is required, so you can use either a local instalation or using Docker.


## MongoDB

### Manual instalation

Follow the instructions in: http://cassandra.apache.org/doc/latest/getting_started/installing.html


### Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. `docker run -d --name mongodb-instance -p 27017:27017 mongodb`

## Couchbase

### Manual instalation

Follow the instructions in: https://www.couchbase.com/get-started-developing-nosql


### Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. `docker run -d --name couchbase-instance -p 8091-8094:8091-8094 -p 11210:11210 couchbase`


## Run the code

With a MongoDB and Couchbase instances running go to the class **App** and have fun.
