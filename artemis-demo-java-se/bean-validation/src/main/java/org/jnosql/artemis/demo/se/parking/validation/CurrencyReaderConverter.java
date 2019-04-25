/*
 * Copyright (c) 2019 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */
package org.jnosql.artemis.demo.se.parking.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

class CurrencyReaderConverter {

    private final List<CurrencyUnit> currencies = new ArrayList<>();

    public CurrencyReaderConverter(CurrencyAccepted currency) {
        currencies.addAll(createCurrencyList(currency.currencies()));
        currencies.addAll(createCurrencyListFromLocale(currency.currenciesFromLocales()));

    }


    private List<CurrencyUnit> createCurrencyList(String[] currenciesTexts) {
        if (isEmptyArray(currenciesTexts)) {
            return Collections.emptyList();
        }
        List<CurrencyUnit> currenciesFromCode = new ArrayList<>();
        for (String value : currenciesTexts) {
            currenciesFromCode.add(Monetary.getCurrency(value.trim()));
        }
        return currenciesFromCode;
    }


    private boolean isEmptyArray(String[] currenciesTexts) {
        return currenciesTexts.length == 0 || currenciesTexts.length == 1 && currenciesTexts[0].isEmpty();
    }

    private List<CurrencyUnit> createCurrencyListFromLocale(
            String[] currenciesTexts) {

        if (isEmptyArray(currenciesTexts)) {
            return Collections.emptyList();
        }
        List<CurrencyUnit> currenciesFromLocale = new ArrayList<>();
        for (String value : currenciesTexts) {
            String[] aux = value.split("_");
            if (aux.length == 2) {
                currenciesFromLocale
                        .add(Monetary.getCurrency(new Locale(aux[0], aux[1])));
            } else {
                throw new IllegalArgumentException(
                        "On error happened on parameter: " + value);
            }
        }
        return currenciesFromLocale;
    }

    List<CurrencyUnit> getCurrencies() {
        return currencies;
    }

}