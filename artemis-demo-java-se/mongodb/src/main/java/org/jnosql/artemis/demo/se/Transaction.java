package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Transaction {

    @Id
    private String txId;

    @Column
    private Currency currency;

    public String getTxId() {
        return txId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Transaction(String txId, Currency currency) {
        this.txId = txId;
        this.currency = currency;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txId='" + txId + '\'' +
                ", currency=" + currency +
                '}';
    }
}
