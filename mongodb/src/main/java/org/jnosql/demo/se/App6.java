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


import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


public class App6 {


    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

          User user = new User();
          user.setNickname("otaviojava");
          user.setBirthday(LocalDate.now());
          user.setSettings(Collections.singletonMap("key", "value"));
          user.setLanguages(Arrays.asList("Portuguese", "English"));

          DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(user);

            Optional<User> otaviojava = template.find(User.class, "otaviojava");
            System.out.println(otaviojava);
        }
    }

    private App6() {
    }
}
