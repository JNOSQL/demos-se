package org.jnosql.artemis.demo.se.column;


import org.jnosql.artemis.cassandra.column.CQL;
import org.jnosql.artemis.cassandra.column.CassandraCrudRepository;

import java.util.List;

public interface MovieRepository extends CassandraCrudRepository<Movie> {


    List<Movie> findByAge(Integer age);

    @CQL("select * from developers.Movie")
    List<Movie> findAll();
}
