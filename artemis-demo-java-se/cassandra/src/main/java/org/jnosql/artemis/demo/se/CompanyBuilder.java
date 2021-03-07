package org.jnosql.artemis.demo.se;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CompanyBuilder {
    private String nickname;

    private Money cost;

    private Set<String> languages = new HashSet<>();

    private Map<String, String> contacts = new HashMap<>();

    private Set<Address> addresses = new HashSet<>();

    public CompanyBuilder withNickname(String nickname) {
        this.nickname = Objects.requireNonNull(nickname, "nickname is required");
        return this;
    }

    public CompanyBuilder withCost(Money cost) {
        this.cost = Objects.requireNonNull(cost, "cost is required");
        return this;
    }

    public CompanyBuilder addLanguage(String language) {
        this.languages.add(Objects.requireNonNull(language, "language is required"));
        return this;
    }

    public CompanyBuilder add(String type, String contact) {
        this.contacts.put(Objects.requireNonNull(type, "type is required"),
                Objects.requireNonNull(contact, "contact is required"));
        return this;
    }

    public CompanyBuilder addAddress(Address address) {
        this.addresses.add(Objects.requireNonNull(address, "address is required"));
        return this;
    }


    public Company build() {
        return new Company(nickname, cost, languages, contacts, addresses);
    }
}