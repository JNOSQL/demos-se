# scylla-demo

An Artemis project with Java SE using using Column API with Scylla as driver implementation.

![Scylla Project](http://www.jnosql.org/img/logos/scylla.svg)

**Scylla**: Scylla is an open-source distributed NoSQL data store. It was designed to be compatible with Apache Cassandra while achieving significantly higher throughputs and lower latencies. It supports the same protocols as Cassandra (CQL and Thrift) and the same file formats (SSTable), but is a completely rewritten implementation, using the C++17 language replacing Cassandra's Java, and the Seastar asynchronous programming library replacing threads, shared memory, mapped files, and other classic Linux programming techniques.

To run this project a Cassandra instance is required, so you can use either a local instalation or using Docker.

## Manual instalation

Follow the instructions in: https://www.scylladb.com/download/open-source/

## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://hub.docker.com/r/scylladb/scylla/
1. Run docker command
1. `docker run -d --name scylladb-instance -p 9042:9042 scylladb/scylla`

## Run the code

With a Cassandra instance running go to the classes **App** and **ScyllaAPP** and have fun.
