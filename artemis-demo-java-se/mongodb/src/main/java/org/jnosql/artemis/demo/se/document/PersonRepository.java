package org.jnosql.artemis.demo.se.document;


import org.jnosql.artemis.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person> {


    List<Person> findByName(String name);
}
