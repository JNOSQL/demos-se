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

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
public class Hero implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String realName;

    @Column
    private Integer age;

    @Column
    private Set<String> powers;

    Hero() {
    }

    Hero(String name, String realName, Integer age, Set<String> powers) {
        this.id = name;
        this.name = name;
        this.realName = realName;
        this.age = age;
        this.powers = powers;
    }


    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public Integer getAge() {
        return age;
    }

    public Set<String> getPowers() {
        if (powers == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(powers);
    }

    public static HeroBuilder builder() {
        return new HeroBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hero hero)) {
            return false;
        }
        return Objects.equals(name, hero.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Hero{" + "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                ", powers=" + powers +
                '}';
    }
}