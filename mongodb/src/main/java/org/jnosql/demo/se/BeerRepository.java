package org.jnosql.demo.se;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.Set;

@Repository
public interface BeerRepository extends PageableRepository<Beer, String> {

    Set<Beer> findByStyle(String style);

    @Query("select * from Beer")
    Set<Beer> query();

}
