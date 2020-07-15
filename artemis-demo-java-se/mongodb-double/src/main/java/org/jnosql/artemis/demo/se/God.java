package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.bson.BsonObjectId;

import java.util.List;

@Entity
public class God {

    @Id
    private BsonObjectId id;

    @Column
    private String name;

    @Column
    private List<String> powers;

    God(BsonObjectId id, String name, List<String> powers) {
        this.id = id;
        this.name = name;
        this.powers = powers;
    }

    public God() {
    }

    public BsonObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPowers() {
        return powers;
    }
}
