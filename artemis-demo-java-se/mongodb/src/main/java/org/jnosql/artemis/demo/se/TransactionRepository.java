package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;

import java.util.List;

public interface TransactionRepository extends Repository<Transaction, String> {

    @Query("select * from Transaction where currency.curencyName = @currencyName")
    List<Transaction> findByQuery(@Param("currencyName") String currencyName);
}
