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
