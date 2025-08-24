package org.jnosql.demo.se;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
}
