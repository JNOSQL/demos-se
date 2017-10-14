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


import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.jnosql.diana.api.document.DocumentCondition.eq;
import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;

@ApplicationScoped
public class SensorRepository {


    private static final String TEMPERATURE = "temperature";
    private static final String SENSORS = "sensors";
    private static final Document SENSOR_ID = Document.of("_id", Device.ID);

    @Inject
    private DocumentTemplate documentTemplate;

    public void save(Sensor sensor) {
        documentTemplate.insert(sensor);
    }


    public List<String> sensors() {

        DocumentQuery query = select().from(SENSORS).where(eq(SENSOR_ID)).build();

        Optional<Device> device = documentTemplate.singleResult(query);
        return device.map(Device::getDevices).orElse(Collections.emptyList());
    }

    public void saveSensors(List<String> sensors) {
        Device device = Device.of(sensors);
        if (sensors.size() == 1) {
            documentTemplate.insert(device);
        } else {
            documentTemplate.update(device);
        }
    }

    public List<Sensor> getSensor(String sensorId) {
        DocumentQuery query = select().from(TEMPERATURE).where(eq(Document.of("sensorId", sensorId))).build();
        return documentTemplate.select(query);
    }

}
