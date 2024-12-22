package org.jnosql.demo.se;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.Set;

@Repository
public interface BeerRepository extends BasicRepository<Beer, String> {

    Set<Beer> findByStyle(String style);

    @Query("select * from Beer")
    Set<Beer> query();

}
