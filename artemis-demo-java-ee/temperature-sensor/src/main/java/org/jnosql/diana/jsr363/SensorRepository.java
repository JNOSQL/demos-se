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


import org.jnosql.artemis.document.DocumentRepository;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SensorRepository {


    private static final String TEMPERATURE = "temperature";
    private static final String SENSORS = "sensors";
    private static final Document SENSOR_ID = Document.of("_id", Device.ID);

    @Inject
    private DocumentRepository repository;

    public void save(Sensor sensor) {
        repository.save(sensor);
    }


    public List<String> sensors() {
        DocumentQuery query = DocumentQuery.of(SENSORS);
        query.and(DocumentCondition.eq(SENSOR_ID));
        Optional<Device> device = repository.singleResult(query);
        return device.map(Device::getDevices).orElse(Collections.emptyList());
    }

    public void saveSensors(List<String> sensors) {
        Device device = Device.of(sensors);
        if (sensors.size() == 1) {
            repository.save(device);
        } else {
            repository.update(device);
        }
    }

    public List<Sensor> getSensor(String sensorId) {
        DocumentQuery query = DocumentQuery.of(TEMPERATURE);
        query.and(DocumentCondition.eq(Document.of("sensorId", sensorId)));
        return repository.find(query);
    }

}
