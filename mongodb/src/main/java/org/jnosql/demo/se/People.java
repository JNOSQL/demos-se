package org.jnosql.demo.se;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface People extends BasicRepository<Person, Long>{
}
