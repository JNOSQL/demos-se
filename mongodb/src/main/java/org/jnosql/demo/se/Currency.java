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

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

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
