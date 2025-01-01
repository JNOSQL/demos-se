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


import com.datastax.oss.driver.api.core.ConsistencyLevel;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.databases.cassandra.mapping.CassandraTemplate;

import java.util.Arrays;
import java.util.List;

public class App3 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Person person = Person.builder().withPhones(Arrays.asList("234", "432"))
                    .withName("Ada Lovelace").withId(1).build();
            CassandraTemplate cassandraTemplate = container.select(CassandraTemplate.class).get();
            Person saved = cassandraTemplate.save(person, ConsistencyLevel.ONE);
            System.out.println("Person saved" + saved);
            List<Person> people = cassandraTemplate.<Person>cql("select * from developers.Person where id = 1")
                    .toList();
            System.out.println("Entity found: " + people);

        }
    }

    private App3() {
    }
}
