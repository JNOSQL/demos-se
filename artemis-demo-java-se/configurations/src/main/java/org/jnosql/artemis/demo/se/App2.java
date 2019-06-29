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

package org.jnosql.artemis.demo.se;


import jakarta.nosql.mapping.document.DocumentTemplate;
import jakarta.nosql.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;

import static jakarta.nosql.document.DocumentQuery.select;

public class App2 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {


            Hero captain = Hero.builder().withRealName("Steve Rogers").withName("captain_america")
                    .withAge(500).withPowers(Collections.singleton("strong")).build();
            XMLResourceProducer xml = container.select(XMLResourceProducer.class).get();
            DocumentTemplate template = xml.getTemplate();
            template.insert(captain);
            DocumentQuery query = select().from("Hero").where("_id").eq("captain_america").build();
            List<Hero> heroes = template.select(query);
            System.out.println(heroes);

        }
    }

    private App2() {
    }
}
