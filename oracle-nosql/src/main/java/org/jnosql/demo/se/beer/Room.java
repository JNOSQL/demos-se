package org.jnosql.demo.se.beer;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
public record Room (@Id int number, @Column Guest guest) {
}
