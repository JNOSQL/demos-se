/*
 * Copyright (c) 2021 Otávio Santana and others
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


import jakarta.nosql.column.ColumnQuery;
import org.eclipse.jnosql.mapping.cassandra.column.CassandraTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

public class App5 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            CassandraTemplate template = container.select(CassandraTemplate.class).get();
            Currency currency = Currency.getInstance(Locale.US);

            Company company = Company.builder()
                    .withName("SouJava")
                    .addLanguage("Portuguese")
                    .addLanguage("English")
                    .addLanguage("Italian")
                    .addLanguage("Spanish")
                    .addHeadquarter(Headquarter.of("Salvador", "Brazil"))
                    .addHeadquarter(Headquarter.of("Sao Paulo", "Brazil"))
                    .addHeadquarter(Headquarter.of("Leiria", "Portugal"))
                    .add("twitter", "otaviojava")
                    .add("linkedin", "otaviojava")
                    .withCost(Money.of(currency, BigDecimal.valueOf(10_000)))
                    .build();

            template.insert(company);
            Optional<Company> soujava = template.find(Company.class, "SouJava");

            System.out.println("the company is " + soujava);


        }
    }

    private App5() {
    }
}
