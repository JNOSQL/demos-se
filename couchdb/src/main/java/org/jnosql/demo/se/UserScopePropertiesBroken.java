/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package org.jnosql.demo.se;

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