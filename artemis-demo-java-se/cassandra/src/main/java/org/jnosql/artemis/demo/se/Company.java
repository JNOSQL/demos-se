package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.jnosql.mapping.cassandra.column.UDT;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {

    @Id
    private String name;

    @Column
    @UDT("money")
    private Money cost;

    @Column
    private Set<String> languages;

    @Column
    private Map<String, String> contacts;

    @Column
    @UDT("address")
    private Set<Address> addresses;

    /**
     * Use the {@link Company#builder()} instead
     */
    @Deprecated
    Company() {
    }

    private Company(String name, Money cost, Set<String> languages, Map<String, String> contacts,
                    Set<Address> addresses) {
        this.name = name;
        this.cost = cost;
        this.languages = languages;
        this.contacts = contacts;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public Money getCost() {
        return cost;
    }

    public Set<String> getLanguages() {
        if (this.languages == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.languages);
    }

    public Map<String, String> getContacts() {
        if (this.contacts == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(contacts);
    }

    public Set<Address> getAddresses() {
        if (this.addresses == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.addresses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", languages=" + languages +
                ", contacts=" + contacts +
                ", addresses=" + addresses +
                '}';
    }

    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }


    public static class CompanyBuilder {
        private String nickname;

        private Money cost;

        private Set<String> languages = new HashSet<>();

        private Map<String, String> contacts = new HashMap<>();

        private Set<Address> addresses = new HashSet<>();

        private CompanyBuilder() {
        }

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
}
