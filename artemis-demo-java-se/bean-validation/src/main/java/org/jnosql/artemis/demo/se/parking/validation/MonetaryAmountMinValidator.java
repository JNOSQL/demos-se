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

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MonetaryAmountMinValidator implements ConstraintValidator<MonetaryMin, MonetaryAmount>{
   private BigDecimal number;

   @Override
   public void initialize(MonetaryMin constraintAnnotation) {
      number = new BigDecimal(constraintAnnotation.value());
   }

   @Override
   public boolean isValid(MonetaryAmount value,
                          ConstraintValidatorContext context) {
      if (value == null) {
         return true;
      }
      return value.isGreaterThanOrEqualTo(value.getFactory().setNumber(number).create());
   }
}
