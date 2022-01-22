package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Car {

    @Id
    private Long id;

    @Column
    private String provider;

    @Column
    private String model;

    @Column
    private Integer year;

    @Column
    private String color;


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}
