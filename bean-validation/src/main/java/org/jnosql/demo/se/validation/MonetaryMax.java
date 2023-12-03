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

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = MonetaryAmountMaxValidator.class)
@Documented
/**
 *Informs the maximum value of a {@link MonetaryAmount}.
 *To do the comparison is used the {@link MonetaryAmount#isLessThanOrEqualTo(MonetaryAmount)
 * @author Otavio Santana
 */
public @interface MonetaryMax {

    String message() default "{org.javamoney.midas.constraints.monetaryMax}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}