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


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("sensors")
public class Device {

    public static final String ID = "devices";

    @Column("_id")
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
        final StringBuilder sb = new StringBuilder("Device{");
        sb.append("id='").append(id).append('\'');
        sb.append(", devices=").append(devices);
        sb.append('}');
        return sb.toString();
    }

    public static Device of(List<String> devices) {
        Device device = new Device();
        device.devices.addAll(devices);
        return device;
    }
}
