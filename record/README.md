# mongodb-demo

![MongoDB Project](http://www.jnosql.org/img/logos/mongodb.png)


**Mongodb**: MongoDB is a free and open-source cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.


To run this project a MongoDB instance is required, so you can use either a local installation or using Docker.


## Manual installation

Follow the instructions in: https://docs.mongodb.com/manual/installation/


## Using Docker

1. Install docker: https://www.docker.com/
2. https://store.docker.com/images/mongo
3. Run docker command
4. Run MongoDB: verify MongoDB image name with the command `docker images`, it can be mongodb or mongo, and then execute this command 
   1. `docker run -d --name mongodb-instance -p 27017:27017 mongo`



## Run the code

With a MongoDB instance running go to the class **App** and have fun.
