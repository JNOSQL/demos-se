package org.jnosql.diana.jsr363.temperature;


import org.jnosql.artemis.column.ColumnRepository;
import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class SensorRepository {

    private static final Logger LOGGER = Logger.getLogger(SensorRepository.class.getName());

    private static final String TEMPERATURE = "temperature";
    private static final String SENSORS = "sensors";
    private static final Column SENSOR_ID = Column.of("id", Device.ID);
    private static final long LIMIT = 20L;

    @Inject
    private ColumnRepository repository;

    public void save(Sensor sensor) {
        LOGGER.info("Saving the sensor: " + sensor);
        repository.saveAsync(sensor);
    }


    public List<String> sensors() {
        ColumnQuery query = ColumnQuery.of(SENSORS);
        query.addCondition(ColumnCondition.eq(SENSOR_ID));
        Optional<Device> device = repository.singleResult(query);
        return device.map(Device::getDevices).orElse(Collections.emptyList());
    }

    public void saveSensors(List<String> sensors) {
        Device device = Device.of(sensors);
        if (sensors.size() == 1) {
            repository.saveAsync(device);
        } else {
            repository.updateAsync(device);
        }
    }

    public List<Sensor> getSensor(String sensorId) {
        ColumnQuery query = ColumnQuery.of(TEMPERATURE);
        query.addCondition(ColumnCondition.eq(Column.of("sensorId", sensorId)));
        query.setLimit(LIMIT);
        return repository.find(query);
    }

}
