package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Currency {
    @Column
    private String ticker;
    @Column
    private String curencyName;

    Currency(String ticker, String curencyName) {
        this.ticker = ticker;
        this.curencyName = curencyName;
    }

    Currency() {
    }

    @Override
    public String toString() {
        return "Currency{" +
                "ticker='" + ticker + '\'' +
                ", curencyName='" + curencyName + '\'' +
                '}';
    }
}
