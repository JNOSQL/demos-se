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

package org.jnosql.artemis.demo.se.cassandra;


import com.datastax.driver.core.ConsistencyLevel;
import org.jnosql.artemis.cassandra.column.CassandraTemplate;
import org.jnosql.diana.api.column.ColumnQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;

import static org.jnosql.diana.api.column.query.ColumnQueryBuilder.select;

public class App3 {

    private static final Person PERSON = Person.builder().
            withPhones(Arrays.asList("234", "432"))
            .withName("Name")
            .withId(1)
            .withIgnore("Just Ignore").build();

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            CassandraTemplate cassandraTemplate = container.select(CassandraTemplate.class).get();
            Person saved = cassandraTemplate.save(PERSON, ConsistencyLevel.ONE);
            System.out.println("Person saved" + saved);

            ColumnQuery query = select().from("Person").where("id").eq(1L).build();

            List<Person> people = cassandraTemplate.cql("select * from developers.Person where id = 1");
            System.out.println("Entity found: " + people);

        }
    }

    private App3() {
    }
}
