package org.jnosql.artemis.demo.se.key;

import java.util.List;


public class UserBuilder {

    private String username;

    private String name;

    private List<String> phones;


    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public UserBuilder withPhones(List<String> phones) {
        this.phones = phones;
        return this;
    }

    public User build() {
        return new User(username, name, phones);
    }
}
