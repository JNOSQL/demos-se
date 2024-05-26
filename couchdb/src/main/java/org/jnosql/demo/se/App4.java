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
import org.eclipse.jnosql.mapping.document.DocumentTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class App4 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Map<String, Object> maps = Collections.singletonMap("1", "2");
            UserScopePropertiesBroken entity1 = new UserScopePropertiesBroken("user", "scope", maps);

            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(entity1);

            final Optional<Object> first1 = template.select(UserScopePropertiesBroken.class)
                    .where("userName")
                    .eq("user").and("scope").eq("scope")
                    .stream()
                    .findFirst();
            System.out.println(first1);
        }
    }

    private App4() {
    }
}
