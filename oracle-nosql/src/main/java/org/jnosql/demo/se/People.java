package org.jnosql.demo.se;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;

@Repository
public interface People extends PageableRepository<Person, Long>{
}
