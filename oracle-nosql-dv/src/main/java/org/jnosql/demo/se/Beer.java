package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import net.datafaker.Faker;

import java.util.Objects;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;




@Entity
public class Beer {

    @Id
    private String id;

    @Column
    private String style;

    @Column
    private String hop;

    @Column
    private String malt;

    @Column
    private List<String> comments;

    @Column
    private List<Crew> crew;

    @Column
    private Map<String, Object> data;


    public String id() {
        return id;
    }

    public String style() {
        return style;
    }

    public String hop() {
        return hop;
    }

    public String malt() {
        return malt;
    }

    public List<String> comments() {
        return comments;
    }

    public List<Crew> crew() {
        return crew;
    }

    public Map<String, Object> data() {
        return data;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", style='" + style + '\'' +
                ", hop='" + hop + '\'' +
                ", malt='" + malt + '\'' +
                ", comments='" + comments + '\'' +
                ", crew='" + crew + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Beer beer = (Beer) object;
        return Objects.equals(id, beer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static Beer of(Faker faker){
        var beer = faker.beer();
        Beer entity = new Beer();
        entity.hop = beer.hop();
        entity.malt = beer.malt();
        entity.style = beer.style();
        entity.id= UUID.randomUUID().toString();
        entity.comments  = List.of("comment1", "comment2");
        entity.crew  = List.of(new Crew("Otavio"));
        entity.data = Map.of("name", "beer");
        //entity.data = Map.of("name", "beer", "price", 50);
        return entity;
    }

}
