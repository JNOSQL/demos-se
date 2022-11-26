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

public class WorkerBuilder {
    private String id;
    private String name;
    private String city;
    private int age;
    private int dailyHours;
    private Gender gender;

    public WorkerBuilder id(String id) {
        this.id = id;
        return this;
    }

    public WorkerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public WorkerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public WorkerBuilder age(int age) {
        this.age = age;
        return this;
    }

    public WorkerBuilder dailyHours(int dailyHours) {
        this.dailyHours = dailyHours;
        return this;
    }

    public WorkerBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Worker build() {
        return new Worker(id, name, city, age, dailyHours, gender);
    }
}