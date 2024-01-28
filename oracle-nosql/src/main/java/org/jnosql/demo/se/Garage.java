package org.jnosql.demo.se;

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

@Repository
public interface Garage extends DataRepository<Car, String>{

    @Save
    Car parking(Car car);

    @Delete
    void unpark(Car car);

    Page<Car> findByTransmission(String transmission, Pageable page);
}
