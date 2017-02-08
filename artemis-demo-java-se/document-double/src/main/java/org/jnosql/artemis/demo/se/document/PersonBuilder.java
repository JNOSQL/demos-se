package org.jnosql.artemis.demo.se.document;

import java.util.List;


public class PersonBuilder {

    private String id;

    private String name;

    private String phone;

    private String ignore;

    public PersonBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public PersonBuilder withPhones(String phone) {
        this.phone = phone;
        return this;
    }

    public PersonBuilder withIgnore(String ignore) {
        this.ignore = ignore;
        return this;
    }

    public Person build() {
        return new Person(id, name, phone, ignore);
    }
}
