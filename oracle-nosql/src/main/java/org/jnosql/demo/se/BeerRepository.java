package org.jnosql.demo.se;

import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import org.eclipse.jnosql.databases.oracle.mapping.OracleNoSQLRepository;

import java.util.Set;

@Repository
public interface BeerRepository extends OracleNoSQLRepository<Beer, String> {

    Set<Beer> findByStyle(String style);

    @Query("select * from Beer")
    Set<Beer> query();

}
