package org.jnosql.demo.se.car;

import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
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

    Page<Car> findByTransmission(String transmission, PageRequest page);
}
