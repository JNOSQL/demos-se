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


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.communication.document.DocumentQuery;
import org.eclipse.jnosql.databases.arangodb.mapping.ArangoDBTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.eclipse.jnosql.communication.document.DocumentQuery.select;

public class App1 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Hero ironMan = Hero.builder().withRealName("Tony Stark").withName("iron_man")
                    .withAge(34).withPowers(Collections.singleton("rich")).build();

            ArangoDBTemplate template = container.select(ArangoDBTemplate.class).get();
            template.insert(ironMan);

            DocumentQuery query = select().from("Hero").where("_key").eq("iron_man").build();
            List<Hero> heroes = template.<Hero>select(query).collect(Collectors.toList());
            List<Hero> aql = template.<Hero>aql("FOR h IN Hero FILTER  h.name == @id RETURN h", Collections.singletonMap("id", "iron_man"))
                    .collect(Collectors.toList());
            System.out.println(heroes);
            System.out.println(aql);



        }
    }
    private App1() {
    }
}
