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

package org.jnosql.artemis.demo.se.elasticsearch;


import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.jnosql.artemis.elasticsearch.document.ElasticsearchTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App3 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Random random = new Random();
            long id = random.nextLong();

            Address address = Address.builder()
                    .withCity("São Paulo")
                    .withStreet("Av. nove de Julho 1854")
                    .withNumber(10).build();

            Developer developer = Developer.builder().
                    withPhones(Arrays.asList("85 85 343435684", "55 11 123448684"))
                    .withName("Maria Lovelace")
                    .withId(id)
                    .withAddress(address)
                    .withLanguage("Java SE")
                    .withLanguage("Java EE")
                    .build();

            ElasticsearchTemplate template = container.select(ElasticsearchTemplate.class).get();
            Developer saved = template.insert(developer);
            System.out.println("Developer saved" + saved);

            TermQueryBuilder query = QueryBuilders.termQuery("phones", "85 85 343435684");

            List<Developer> people = template.search(query);
            System.out.println("Entity found: " + people);

        }
    }

    private App3() {
    }
}
