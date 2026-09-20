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
import org.eclipse.jnosql.mapping.timeseries.TimeSeriesTemplate;

import java.math.BigDecimal;
import java.time.Instant;

public class App {

    public static void main(String[] args) {

        var firstTransaction = new AccountTransaction(
                Instant.parse("2026-09-20T08:00:00Z"),
                "account-42",
                new BigDecimal("79.90"),
                "EUR",
                TransactionStatus.APPROVED
        );

        var secondTransaction = new AccountTransaction(
                Instant.parse("2026-09-20T09:00:00Z"),
                "account-42",
                new BigDecimal("24.50"),
                "EUR",
                TransactionStatus.APPROVED
        );

        var latestTransaction = new AccountTransaction(
                Instant.parse("2026-09-20T10:15:00Z"),
                "account-42",
                new BigDecimal("120.00"),
                "EUR",
                TransactionStatus.DECLINED
        );

        try (SeContainer container =
                     SeContainerInitializer.newInstance().initialize()) {

            TimeSeriesTemplate template =
                    container.select(TimeSeriesTemplate.class).get();

            template.insert(firstTransaction);
            template.insert(secondTransaction);

            AccountTransaction saved =
                    template.insert(latestTransaction);

            var currentStatus = template
                    .select(AccountTransaction.class)
                    .where("account")
                    .eq("account-42")
                    .orderBy("id")
                    .desc()
                    .limit(1)
                    .singleResult();

            System.out.println(
                    "Current account status: " + currentStatus
            );

            var history = template
                    .select(AccountTransaction.class)
                    .where("account")
                    .eq("account-42")
                    .orderBy("id")
                    .desc()
                    .skip(1)
                    .limit(10)
                    .result();

            System.out.println("Transaction history:");
            history.forEach(System.out::println);
        }

        }

    private App() {
    }
}
