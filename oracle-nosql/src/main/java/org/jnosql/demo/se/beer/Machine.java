package org.jnosql.demo.se.beer;

import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.nosql.Column;
import jakarta.nosql.Convert;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
@JsonbVisibility(FieldAccessStrategy.class)
public class Machine {

    @Id
    private String id;

    @Column
    @Convert(EngineConverter.class)
    private Engine engine;

    @Column
    private String manufacturer;

    @Column
    private int year;

}
