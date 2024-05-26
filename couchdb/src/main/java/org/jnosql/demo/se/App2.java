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
import org.eclipse.jnosql.mapping.PreparedStatement;

import org.eclipse.jnosql.mapping.document.DocumentTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class App2 {



    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Hero ironMan = Hero.builder().withRealName("Tony Stark").withName("iron_man")
                    .withAge(34).withPowers(Collections.singleton("rich")).build();
            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.update(ironMan);

            PreparedStatement prepare = template.prepare("select * from Hero where realName =@name");
            List<Hero> heroes = prepare.bind("name", "Tony Stark")
                    .<Hero>result().collect(Collectors.toList());
            System.out.println(heroes);
            System.out.println(template.query("select * from Hero where _id = 'iron_man'"));

        }
    }

    private App2() {
    }
}
