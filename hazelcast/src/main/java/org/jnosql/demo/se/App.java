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
import org.eclipse.jnosql.mapping.keyvalue.KeyValueTemplate;

import java.util.Arrays;
import java.util.Optional;

public class App {

    private static final User USER = User.builder().
            withPhones(Arrays.asList("234", "432"))
            .withUsername("username")
            .withName("Name")
            .build();

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            KeyValueTemplate template = container.select(KeyValueTemplate.class).get();
            User userSaved = template.put(USER);
            System.out.println("User saved: " + userSaved);
            Optional<User> user = template.get("username", User.class);
            System.out.println("Entity found: " + user);

        }
    }

    private App() {
    }
}
