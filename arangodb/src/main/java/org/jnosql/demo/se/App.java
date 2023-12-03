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
import jakarta.nosql.document.DocumentTemplate;
import net.datafaker.Faker;

import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            var faker = new Faker();
            Hero ironMan = Hero.of(faker);
            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(ironMan);

            List<Hero> heroes = template.select(Hero.class)
                    .where("name").eq(faker.name()).result();
            System.out.println(heroes);

        }
    }

    private App() {
    }
}
