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

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

public class CurrencyApp {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Currency currency = new Currency("USA", "USD");
            Transaction transaction = new Transaction("user", currency);

            TransactionRepository repository = container.select(TransactionRepository.class).get();

            repository.save(new Transaction("user", currency));
            repository.save(new Transaction("user2", currency));
            repository.save(new Transaction("user3", currency));

            final List<Transaction> usd = repository.findByQuery("USD");
            System.out.println(usd);


        }
    }


    private CurrencyApp() {
    }
}
