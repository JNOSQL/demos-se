/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */

package org.jnosql.artemis.demo.se.graph;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import java.util.Objects;


@Entity
public class Person {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Long age;

    @Column
    private String occupation;

    @Column
    private Double salary;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Long getAge() {
        return age;
    }

    Person() {
    }

    Person(String name, Long age, String occupation, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", occupation='").append(occupation).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {

        private String name;

        private Long age;

        private String occupation;

        private Double salary;


        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withAge(Long age) {
            this.age = age;
            return this;
        }

        public PersonBuilder withOccupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public PersonBuilder withSalary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Person build() {
            return new Person(name, age, occupation, salary);
        }
    }

}
