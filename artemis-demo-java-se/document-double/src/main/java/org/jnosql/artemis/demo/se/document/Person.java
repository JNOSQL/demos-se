package org.jnosql.artemis.demo.se.document;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;


@Entity
public class Person {

    @Column("_id")
    private String id;

    @Column
    private String name;

    @Column
    private String phone;

    private String ignore;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Person(String phone) {
        this.phone = phone;
    }

    public String getIgnore() {
        return ignore;
    }

    public Person() {
    }

    Person(String id, String name, String phone, String ignore) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("phone", phone)
                .append("ignore", ignore)
                .toString();
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }
}
