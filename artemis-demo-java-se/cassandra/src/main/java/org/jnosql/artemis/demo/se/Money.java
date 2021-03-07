package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Convert;
import jakarta.nosql.mapping.Entity;

import java.math.BigDecimal;
import java.util.Currency;

@Entity
public class Money {

    @Column
    @Convert(MoneyConverter.class)
    private Currency currency;

    @Column
    private BigDecimal amount;
}
