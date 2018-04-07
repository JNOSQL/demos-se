# elasticsearch-demo

A JNoSQL Atemis project with Java SE using Document API with MongoDB as driver implementation.

![Elasticsearch Project](https://jnosql.github.io/img/logos/elastic.svg)


**Elasticsearch**: Elasticsearch is a search engine based on Lucene. It provides a distributed, multitenant-capable full-text search engine with an HTTP web interface and schema-free JSON documents. Elasticsearch is developed in Java and is released as open source under the terms of the Apache License. Elasticsearch is the most popular enterprise search engine followed by Apache Solr, also based on Lucene.


To run this project an Elasticsearch instance is required, so you can use either a local installation or using Docker.


## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. `docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.2.3`



## Run the code

With a ES instance running go to the class **App** and have fun.
