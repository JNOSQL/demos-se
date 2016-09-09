package org.jnosql.artemis.demo.se.document;


import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;


@Entity
public class Person {

    @Column("_id")
    private long id;

    @Column
    private String name;

    @Column
    private List<String> phones;

    private String ignore;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<String> getPhones() {
        return phones;
    }

    public String getIgnore() {
        return ignore;
    }

    public Person() {
    }

    Person(long id, String name, List<String> phones, String ignore) {
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
