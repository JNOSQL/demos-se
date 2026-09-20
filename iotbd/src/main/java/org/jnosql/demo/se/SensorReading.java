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

import java.time.Instant;

@Entity
public class SensorReading {

    @Id
    private Instant id;

    @Column
    private String sensor;

    @Column
    private double temperature;

    @Column
    private double humidity;

    SensorReading(Instant id, String sensor, double temperature, double humidity) {
        this.id = id;
        this.sensor = sensor;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    SensorReading() {
    }

    public Instant getId() {
        return id;
    }

    public String getSensor() {
        return sensor;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "id=" + id +
                ", sensor='" + sensor + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}
