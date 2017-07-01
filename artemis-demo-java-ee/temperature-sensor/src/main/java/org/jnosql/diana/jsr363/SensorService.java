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


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class SensorService {

    @Inject
    private SensorRepository repository;

    @Inject
    private List<String> sensors;

    @PostConstruct
    public void init() {
        sensors.addAll(repository.sensors().stream().distinct().sorted().collect(Collectors.toList()));
    }

    public void save(Sensor sensor) {
        repository.save(sensor);
        if (!sensors.contains(sensor.getSensorId())) {
            sensors.add(sensor.getSensorId());
            repository.saveSensors(new ArrayList<>(sensors));
        }

    }

    public List<String> getSensors() {
        return sensors;
    }

    public List<Sensor> getSensor(String sensorId) {
        return repository.getSensor(sensorId);
    }
}
