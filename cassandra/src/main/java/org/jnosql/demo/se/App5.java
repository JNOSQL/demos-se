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


import org.eclipse.jnosql.databases.cassandra.mapping.CassandraTemplate;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

public class App5 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            CassandraTemplate template = container.select(CassandraTemplate.class).get();
            Currency currency = Currency.getInstance(Locale.US);

            Company company = Company.builder()
                    .name("SouJava")
                    .add(Headquarter.of("Salvador", "Brazil"))
                    .add(Headquarter.of("Sao Paulo", "Brazil"))
                    .add(Headquarter.of("Leiria", "Portugal"))
                    .build();

            template.insert(company);
            Optional<Company> soujava = template.find(Company.class, "SouJava");

            System.out.println("the company is " + soujava);


        }
    }

    private App5() {
    }
}
