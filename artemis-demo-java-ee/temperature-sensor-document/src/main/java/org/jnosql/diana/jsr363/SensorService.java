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
