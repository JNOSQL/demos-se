/*
 * Copyright (c) 2017 Otávio Santana and others
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

package org.jnosql.diana.jsr363.producers;


import tec.uom.se.quantity.DefaultQuantityFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.measure.quantity.Temperature;
import javax.measure.spi.QuantityFactory;

@ApplicationScoped
class QuantityFactoryProducer {

    private QuantityFactory<Temperature> factory;


    @PostConstruct
    public void init() {
        this.factory = DefaultQuantityFactory.getInstance(Temperature.class);
    }

    @Produces
    public QuantityFactory<Temperature> getTemperatureFactory() {
        return factory;
    }

}
