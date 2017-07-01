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

package org.jnosql.diana.jsr363;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.measure.Quantity;
import javax.measure.quantity.Temperature;
import org.apache.commons.lang3.StringUtils;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import tec.uom.se.ComparableQuantity;
import tec.uom.se.format.QuantityFormat;

@Entity("temperature")
public class Sensor {


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM HH:mm:ss");

    @Column
    private String sensorId;

    @Column
    private LocalDateTime time;

    @Column("temperature")
    private String temperatureText;

    private Quantity<Temperature> temperature;


    public Sensor() {
    }

    Sensor(String sensorId, LocalDateTime time, Quantity<Temperature> temperature) {
        this.sensorId = sensorId;
        this.time = time;
        this.temperature = temperature;
        if (temperature != null) {
            temperatureText = temperature.toString();
        }
    }

    public String getSensorId() {
        return sensorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getFormatedDate() {
        return DATE_TIME_FORMATTER.format(time);
    }

    public Quantity<Temperature> getTemperature() {
        if (temperature == null && StringUtils.isNotBlank(temperatureText)) {
            QuantityFormat instance = QuantityFormat.getInstance();
            temperature = (Quantity<Temperature>) instance.parse(temperatureText);
        }
        return temperature;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) o;
        return Objects.equals(sensorId, sensor.sensorId) &&
                Objects.equals(time, sensor.time) &&
                Objects.equals(temperature, sensor.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, time, temperature);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sensor{");
        sb.append("sensorId='").append(sensorId).append('\'');
        sb.append(", time=").append(time);
        sb.append(", temperature=").append(temperature);
        sb.append('}');
        return sb.toString();
    }


    public static SensorBuilder builder() {
        return new SensorBuilder();
    }

    public static class SensorBuilder {

        private String sensorId;

        private LocalDateTime time;

        private Quantity<Temperature> temperature;

        private SensorBuilder() {
        }

        public SensorBuilder withSensorId(String sensorId) {
            this.sensorId = sensorId;
            return this;
        }

        public SensorBuilder withTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public SensorBuilder withTemperature(Quantity<Temperature> temperature) {
            this.temperature = temperature;
            return this;
        }

        public Sensor build() {
            return new Sensor(sensorId, time, temperature);
        }
    }
}
