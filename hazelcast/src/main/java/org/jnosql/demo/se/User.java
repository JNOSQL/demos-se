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


import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
public class User implements Serializable {


    @Id
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

    User() {
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
        return "User{" + "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                '}';
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}
