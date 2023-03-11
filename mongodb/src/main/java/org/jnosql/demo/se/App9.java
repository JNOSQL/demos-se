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

import org.eclipse.jnosql.communication.document.DocumentQuery;
import jakarta.nosql.document.DocumentTemplate;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

import static org.eclipse.jnosql.communication.document.DocumentQuery.select;

public class App9 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Citizen salvador = Citizen.of("1", "Salvador");

            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            Citizen insert = template.insert(salvador);

            final DocumentQuery query = select().from(Citizen.class.getSimpleName())
                    .where("name").eq("Salvador").build();
            final Optional<Citizen> citizen = template.singleResult(query);
            System.out.println(citizen);

            template.delete(Citizen.class, "1");
        }
    }

    private App9() {
    }
}
