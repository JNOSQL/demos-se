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

package org.jnosql.artemis.demo.se;


import org.jnosql.artemis.arangodb.document.ArangoDBTemplate;
import jakarta.nosql.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;

import static jakarta.nosql.document.DocumentQuery.select;

public class App1 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Hero ironMan = Hero.builder().withRealName("Tony Stark").withName("iron_man")
                    .withAge(34).withPowers(Collections.singleton("rich")).build();

            ArangoDBTemplate template = container.select(ArangoDBTemplate.class).get();
            template.insert(ironMan);

            DocumentQuery query = select().from("Hero").where("_key").eq("iron_man").build();
            Stream<Hero> heroes = template.select(query);
            Stream<Hero> aql = template.aql("FOR h IN Hero FILTER  h.name == @id RETURN h", Collections.singletonMap("id", "iron_man"));
            System.out.println(heroes);
            System.out.println(aql);



        }
    }
    private App1() {
    }
}
