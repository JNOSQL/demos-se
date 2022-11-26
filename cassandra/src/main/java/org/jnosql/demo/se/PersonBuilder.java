package org.jnosql.demo.se;

import java.util.List;

public class PersonBuilder {
    private long id;
    private String name;
    private List<String> phones;
    private String ignore;

    PersonBuilder() {
    }

    public PersonBuilder id(long id) {
        this.id = id;
        return this;
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder phones(List<String> phones) {
        this.phones = phones;
        return this;
    }

    public PersonBuilder ignore(String ignore) {
        this.ignore = ignore;
        return this;
    }

    public Person build() {
        return new Person(id, name, phones, ignore);
    }
}