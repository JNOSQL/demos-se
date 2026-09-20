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

        var transaction =
                new AccountTransaction(
                        Instant.now(),
                        "account-42",
                        new BigDecimal("79.90"),
                        "EUR",
                        TransactionStatus.APPROVED
                );
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            TimeSeriesTemplate template =
                    container.select(TimeSeriesTemplate.class).get();

            AccountTransaction saved = template.insert(transaction);
            System.out.println("Transaction insert: " + saved);

            var foundTransaction = template.find(AccountTransaction.class,
                    transaction.getId());

            System.out.println("Transaction found: " + foundTransaction);

        }
    }

    private App() {
    }
}
