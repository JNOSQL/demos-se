package org.jnosql.artemis.demo.se.key;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Key;


@Entity
public class User implements Serializable {

    @Column
    @Key
    private String userName;

    private String name;

    private List<String> phones;

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhones() {
        return phones;
    }

    User(String userName, String name, List<String> phones) {
        this.userName = userName;
        this.name = name;
        this.phones = phones;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("userName", userName)
                .append("name", name)
                .append("phones", phones)
                .toString();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}
