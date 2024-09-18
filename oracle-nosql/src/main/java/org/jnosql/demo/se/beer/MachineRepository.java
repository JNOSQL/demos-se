package org.jnosql.demo.se.beer;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends BasicRepository<Machine, String> {

    @Query("from Machine where engine.type = :type")
    List<Machine> findByType(@Param("type") String type);
}
