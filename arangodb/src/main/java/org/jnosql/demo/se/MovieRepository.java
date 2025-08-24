package org.jnosql.demo.se;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findByTitleContains(String title);

    List<Movie> findByTitleStartsWith(String title);

    List<Movie> findByTitleEndsWith(String title);
}
