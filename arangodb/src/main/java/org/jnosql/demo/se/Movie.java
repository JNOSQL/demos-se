package org.jnosql.demo.se;

import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.nosql.Column;

@Entity
public record Movie(@Id String id, @Column String title, @Column String director, @Column int releaseYear) {

}
