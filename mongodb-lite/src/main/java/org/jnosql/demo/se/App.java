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
import net.datafaker.Faker;


public class App {


    public static void main(String[] args) {

        var faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
        DocumentTemplate template = container.select(DocumentTemplate.class).get();

            for (int index = 0; index < 10; index++) {
                Product product = Product.of(faker);
                template.insert(product);
            }

            template.select(Product.class).result().forEach(System.out::println);

        }
    }

    private App() {
    }
}
