package org.jnosql.demo.se.beer;

import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.Optional;

@Repository
public interface Hotel {

    @Save
    Room checkIn(Room room);

    @Delete
    void checkOut(Room room);

    @Find
    Optional<Room> reservation(@By("_id") String number);

    Page<Room> findBy(PageRequest pageRequest);
}
