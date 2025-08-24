package org.jnosql.demo.se;

import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.nosql.Column;

import java.util.UUID;

@Entity
public record Movie(@Id UUID id, @Column String title, @Column int releaseYear) {


    public static Movie of(String title, int releaseYear) {
        return new Movie(UUID.randomUUID(), title, releaseYear);
    }
}
