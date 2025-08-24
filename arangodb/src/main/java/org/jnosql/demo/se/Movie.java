package org.jnosql.demo.se;

import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.nosql.Column;

@Entity
public class Movie {

    @Id
    private String id;

    @Column
    private String title;

    @Column
    private String director;

    @Column
    private int releaseYear;
}
