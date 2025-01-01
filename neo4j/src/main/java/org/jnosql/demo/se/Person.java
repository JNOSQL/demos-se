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
        if (!(o instanceof Person person)) {
            return false;
        }
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", salary=" + salary +
                '}';
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
