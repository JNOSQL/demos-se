package org.jnosql.artemis.demo.se;

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
}
