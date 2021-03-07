package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.jnosql.mapping.cassandra.column.UDT;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {

    @Id
    private String nickname;

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

    @Deprecated
    Company() {
    }

    Company(String nickname, Money cost, Set<String> languages, Map<String, String> contacts,
            Set<Address> addresses) {
        this.nickname = nickname;
        this.cost = cost;
        this.languages = languages;
        this.contacts = contacts;
        this.addresses = addresses;
    }

    public String getNickname() {
        return nickname;
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
        if(this.addresses == null) {
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
        return Objects.equals(nickname, company.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }

    @Override
    public String toString() {
        return "Company{" +
                "nickname='" + nickname + '\'' +
                ", cost=" + cost +
                ", languages=" + languages +
                ", contacts=" + contacts +
                ", addresses=" + addresses +
                '}';
    }
}
