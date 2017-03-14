package org.jnosql.artemis.demo.se.column;


import org.jnosql.artemis.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person> {

    Optional<Person> findById(Long id);
}
