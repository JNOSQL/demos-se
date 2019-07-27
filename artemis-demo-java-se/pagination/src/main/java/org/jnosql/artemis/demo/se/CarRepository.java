package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Page;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Repository;

import java.util.List;

public interface CarRepository extends Repository<Car, Long> {

    List<Car> findByOrderByProvider(Pagination pagination);

    Page<Car> findAll(Pagination pagination);
}
