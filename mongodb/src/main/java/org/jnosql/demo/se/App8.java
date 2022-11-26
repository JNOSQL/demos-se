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

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

import static jakarta.nosql.document.DocumentQuery.select;

public class App8 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            City elSalvador = City.of("1", "El Salvador");
            Citizen salvador = Citizen.of("1", "Salvador", elSalvador);

            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(salvador);

            final DocumentQuery query = select().from(Citizen.class.getSimpleName())
                    .where("name").eq("Salvador")
                    .and("city.name").eq("El Salvador").build();
            final Optional<Citizen> citizen = template.singleResult(query);
            System.out.println(citizen);

            template.delete(Citizen.class, "1");
        }
    }

    private App8() {
    }
}
