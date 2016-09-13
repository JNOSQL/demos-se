package org.jnosql.diana.jsr363;



import javax.measure.Quantity;
import javax.measure.quantity.Temperature;
import javax.measure.spi.QuantityFactory;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static tec.uom.se.format.QuantityFormat.getInstance;
import static tec.uom.se.unit.Units.CELSIUS;

public class SensorRepresentation implements Serializable {

    static final ZoneOffset TIME_ZONE = ZoneOffset.UTC;

    private String sensorId;

    private Long time;

    private String quantity;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Quantity<Temperature> toQuantity(QuantityFactory<Temperature> factory) {
        if (isNotBlank(quantity)) {
            return (Quantity<Temperature>) getInstance().parse(quantity);
        }
        return factory.create(0, CELSIUS);
    }


    public Sensor toSensor(QuantityFactory<Temperature> factory) {
        return Sensor.builder()
                .withSensorId(sensorId)
                .withTemperature(toQuantity(factory))
                .withTime(LocalDateTime.ofEpochSecond(time, 0, TIME_ZONE)).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensorRepresentation)) {
            return false;
        }
        SensorRepresentation that = (SensorRepresentation) o;
        return Objects.equals(sensorId, that.sensorId) &&
                Objects.equals(time, that.time) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, time, quantity);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorRepresentation{");
        sb.append("sensorId='").append(sensorId).append('\'');
        sb.append(", time=").append(time);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

    public static SensorRepresentation of(Sensor sensor) {
        SensorRepresentation representation = new SensorRepresentation();
        representation.setQuantity(sensor.getTemperature().toString());
        representation.setSensorId(sensor.getSensorId());
        representation.setTime(sensor.getTime().toEpochSecond(TIME_ZONE));
        return representation;
    }
}
