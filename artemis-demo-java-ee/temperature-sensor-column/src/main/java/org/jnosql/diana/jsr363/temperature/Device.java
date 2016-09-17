package org.jnosql.diana.jsr363.temperature;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

@Entity("sensors")
public class Device {

    public static final String ID = "devices";

    @Column("id")
    private String id = ID;

    @Column
    private List<String> devices = new ArrayList<>();

    public String getId() {
        return id;
    }

    public List<String> getDevices() {
        return devices;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(id, device.id) &&
                Objects.equals(devices, device.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, devices);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("devices", devices)
                .toString();
    }

    public static Device of(List<String> devices) {
        Device device = new Device();
        device.devices.addAll(devices);
        return device;
    }
}
