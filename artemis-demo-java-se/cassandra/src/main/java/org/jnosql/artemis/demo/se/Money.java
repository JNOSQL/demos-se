package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Convert;
import jakarta.nosql.mapping.Entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Entity
public class Money {

    @Column
    @Convert(MoneyConverter.class)
    private Currency currency;

    @Column
    private BigDecimal amount;

    @Deprecated
    Money() {
    }

    private Money(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(currency, money.currency) && Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return currency.getSymbol() + " "+ amount;
    }

    public static Money of(Currency currency, BigDecimal amount) {
        Objects.requireNonNull(currency, "currency is required");
        Objects.requireNonNull(amount, "amount is required");
        return new Money(currency, amount);
    }
}
