package org.jnosql.diana.jsr363;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jnosql.artemis.document.DocumentCrudOperation;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

@ApplicationScoped
public class SensorRepository {


    private static final String TEMPERATURE = "temperature";
    private static final String SENSORS = "sensors";
    private static final Document SENSOR_ID = Document.of("_id", Device.ID);

    @Inject
    private DocumentCrudOperation crudOperation;

    public void save(Sensor sensor) {
        crudOperation.save(sensor);
    }


    public List<String> sensors() {
        DocumentQuery query = DocumentQuery.of(SENSORS);
        query.addCondition(DocumentCondition.eq(SENSOR_ID));
        Optional<Device> device = crudOperation.singleResult(query);
        return device.map(Device::getDevices).orElse(Collections.emptyList());
    }

    public void saveSensors(List<String> sensors) {
        Device device = Device.of(sensors);
        if (sensors.size() == 1) {
            crudOperation.save(device);
        } else {
            crudOperation.update(device);
        }
    }

    public List<Sensor> getSensor(String sensorId) {
        DocumentQuery query = DocumentQuery.of(TEMPERATURE);
        query.addCondition(DocumentCondition.eq(Document.of("sensorId", sensorId)));
        return crudOperation.find(query);
    }

}
