package org.jnosql.demo.se;

import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.nosql.Column;

@Entity
public record Movie(@Id String id, @Column String title, @Column int releaseYear) {


    public static Movie of(String title, int releaseYear) {
        return new Movie(null, title, releaseYear);
    }
}
