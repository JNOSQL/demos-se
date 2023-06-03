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
package org.jnosql.demo.se.validation;


import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.binarySearch;
import static java.util.Collections.sort;

public class MonetaryAmountAcceptedValidator implements ConstraintValidator<CurrencyAccepted, MonetaryAmount>{


    private final List<CurrencyUnit> currencies = new ArrayList<>();

    @Override
    public void initialize(CurrencyAccepted constraintAnnotation) {
        CurrencyReaderConverter reader = new CurrencyReaderConverter(constraintAnnotation);
        currencies.addAll(reader.getCurrencies());
        sort(currencies);
    }

    @Override
    public boolean isValid(MonetaryAmount value,
                           ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return containsCurrency(value.getCurrency());
    }

    private boolean containsCurrency(CurrencyUnit value) {
        return binarySearch(currencies, value) >= 0;
    }
}
