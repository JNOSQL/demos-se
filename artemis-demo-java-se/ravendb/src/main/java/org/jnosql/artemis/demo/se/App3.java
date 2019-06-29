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


import org.jnosql.artemis.DatabaseQualifier;
import org.jnosql.artemis.PreparedStatement;
import org.jnosql.artemis.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

public class App3 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {


            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            PersonRepository repository = container.select(PersonRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();

            PreparedStatement prepare = template.prepare("select * from Person where name = @name");
            prepare.bind("name", "Tony stark");
            List<Person> people = prepare.getResultList();
            System.out.println("Person from name: " + people);
            System.out.println("Person from age: " + repository.findByAge(30));

        }
    }

    private App3() {
    }
}
