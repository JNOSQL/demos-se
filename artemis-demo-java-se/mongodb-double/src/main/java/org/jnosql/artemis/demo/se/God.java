package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.bson.BsonObjectId;

import java.util.List;

@Entity
public class God {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private List<String> powers;

    God(String name, List<String> powers) {
        this.name = name;
        this.powers = powers;
    }

    public God() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPowers() {
        return powers;
    }

    @Override
    public String toString() {
        return "God{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", powers=" + powers +
                '}';
    }
}
