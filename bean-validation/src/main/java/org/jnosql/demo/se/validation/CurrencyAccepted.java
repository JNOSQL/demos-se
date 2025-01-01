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


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Informs the currencies that are allowed on validation.
 * This annotation works with MonetaryAmount and CurrencyUnit.
 * @author Otavio Santana
 * @author Werner Keil
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {MonetaryAmountAcceptedValidator.class})
@Documented
public @interface CurrencyAccepted {

    String message() default "{org.javamoney.midas.constraints.currencyAccepted}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    /**
     * Informs the currency code.
     * Ex: 'USD', 'BRL', 'EUR'
     * @return currencies allowed using the currency code
     */
    String[] currencies() default "";
    /**
     * Informs the Locale where the currencies are from.
     * Ex: 'en_US','pt_BR', 'en_GB'
     * @return currencies allowed using the currency code
     */
    String[] currenciesFromLocales() default "";
}