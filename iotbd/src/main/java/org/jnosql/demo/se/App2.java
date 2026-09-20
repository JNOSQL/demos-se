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


import jakarta.data.Limit;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.math.BigDecimal;
import java.time.Instant;

public class App2 {

    public static void main(String[] args) {

        var firstTransaction = new AccountTransaction(
                Instant.now(),
                "account-42",
                79.90,
                "EUR",
                TransactionStatus.APPROVED
        );

        var secondTransaction = new AccountTransaction(
                Instant.now(),
                "account-42",
                24.50,
                "EUR",
                TransactionStatus.APPROVED
        );

        var latestTransaction = new AccountTransaction(
                Instant.now(),
                "account-42",
                120.00,
                "EUR",
                TransactionStatus.DECLINED
        );

        try (SeContainer container =
                     SeContainerInitializer.newInstance().initialize()) {

            AccountTransactionRepository repository =
                    container.select(AccountTransactionRepository.class).get();

            repository.save(firstTransaction);
            repository.save(secondTransaction);
            repository.save(latestTransaction);

            var currentStatus = repository
                    .findByAccountOrderByIdDesc(
                            "account-42",
                            Limit.of(1)
                    )
                    .stream()
                    .findFirst();

            System.out.println(
                    "Current account status: " + currentStatus
            );

            var history = repository
                    .findByAccountOrderByIdDesc(
                            "account-42",
                            Limit.range(2, 10)
                    );

            System.out.println("Recent transaction history:");
            history.forEach(System.out::println);
        }
    }

    private App2() {
    }
}
