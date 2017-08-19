# casandra-demo

A diana project with Java SE using using Column API with Cassandra as driver implementation.

![Cassandra Project](https://github.com/JNOSQL/jnosql-site/blob/master/assets/img/logos/cassandra.png)

**Cassandra**: Apache Cassandra is a free and open-source distributed database management system designed to handle large amounts of data across many commodity servers, providing high availability with no single point of failure.


To run this project a Cassandra instance is required, so you can use either a local instalation or using Docker.


## Manual instalation

Follow the instructions in: http://cassandra.apache.org/doc/latest/getting_started/installing.html


## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/cassandra
1. Run docker command
1. `docker run -d --name casandra-instance -p 9042:9042 cassandra`



## Run the code

With a Cassandra instance running go to the classes **App** and **CassandraAPP** and have fun.
