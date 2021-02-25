package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity("user_scope_properties_broken")
public class UserScopePropertiesBroken {

    @Id
    private String userName;

    @Column("scope")
    private String scope;

    @Column("properties")
    private Map<String, Object> properties = new HashMap<>();


    public UserScopePropertiesBroken(String userName, String scope, Map<String, Object> properties) {
        this.userName = userName;
        this.scope = scope;
        this.properties = properties;
    }

    UserScopePropertiesBroken() {
    }

    public String getUserName() {
        return userName;
    }

    public String getScope() {
        return scope;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserScopePropertiesBroken that = (UserScopePropertiesBroken) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }

    @Override
    public String toString() {
        return "UserScopePropertiesBroken{" +
                "userName='" + userName + '\'' +
                ", scope='" + scope + '\'' +
                ", properties=" + properties +
                '}';
    }
}