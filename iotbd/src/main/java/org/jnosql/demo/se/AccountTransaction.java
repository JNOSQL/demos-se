/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class AccountTransaction {

    @Id
    private Instant id;

    @Column
    private String account;

    @Column
    private Double amount;

    @Column
    private String currency;

    @Column
    private TransactionStatus status;

    AccountTransaction(Instant id, String account, Double amount,
                       String currency,
                       TransactionStatus status) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

     AccountTransaction() {
    }

    public Instant getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                '}';
    }
}
