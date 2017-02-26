package org.jnosql.artemis.demo.se.document;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.util.List;


@Entity
public class Person {

    @Column("_id")
    private String id;

    @Column
    private String name;

    @Column
    private List<String> phones;

    private String ignore;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public String getIgnore() {
        return ignore;
    }

    public Person() {
    }

    Person(String id, String name, List<String> phones, String ignore) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("phones", phones)
                .append("ignore", ignore)
                .toString();
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }
}
