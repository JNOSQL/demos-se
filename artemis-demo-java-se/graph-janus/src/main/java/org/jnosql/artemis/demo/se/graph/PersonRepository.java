package org.jnosql.artemis.demo.se.graph;

import org.jnosql.artemis.Repository;

import java.util.stream.Stream;

public interface PersonRepository extends Repository<Person, Long> {

    Stream<Person> findByOccupationAndSalaryGreaterThan(String ocuppation, Double salary);

    Stream<Person> findByAgeBetween(Integer ageA, Integer ageB);

}

