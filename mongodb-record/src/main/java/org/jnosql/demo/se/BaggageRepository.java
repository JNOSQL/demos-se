package org.jnosql.demo.se;

import jakarta.data.repository.By;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BaggageRepository {

    @Save
    Baggage register(Baggage baggage);

    default void registerAll(Baggage... baggages) {
        Arrays.stream(baggages).forEach(this::register);
    }

    @Find
    Baggage findByTicket(@By("ticket") String ticket);

    @Find
    List<Baggage> findAll();

    @Delete
    void unregister(Baggage baggage);

    default void unregisterAll(List<Baggage> baggageList){
        baggageList.forEach(this::unregister);
    }
}
