/*
 * Copyright (c) 2019 Otávio Santana and others
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

package org.jnosql.artemis.demo.se.couchbase;


import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;

import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;

public class App3 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {


            Hero hulk = Hero.builder().withRealName("Bruce Banner").withName("hulk")
                    .withAge(50).withPowers(Collections.singleton("smash")).build();
            YAMLResourceProducer yaml = container.select(YAMLResourceProducer.class).get();
            DocumentTemplate template = yaml.getTemplate();
            template.insert(hulk);
            DocumentQuery query = select().from("Hero").where("_id").eq("hulk").build();
            List<Hero> heroes = template.select(query);
            System.out.println(heroes);

        }
    }

    private App3() {
    }
}
