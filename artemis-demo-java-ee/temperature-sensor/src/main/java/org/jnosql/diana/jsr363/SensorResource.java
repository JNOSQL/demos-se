package org.jnosql.diana.jsr363;


import tec.uom.se.unit.Units;

import javax.inject.Inject;
import javax.measure.quantity.Temperature;
import javax.measure.spi.QuantityFactory;
import javax.ws.rs.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jnosql.diana.jsr363.SensorRepresentation.TIME_ZONE;

@Path("api/v1/sensors")
@Produces(APPLICATION_JSON + ";charset=UTF-8")
@Consumes(APPLICATION_JSON + ";charset=UTF-8")
public class SensorResource {

    @Inject
    private QuantityFactory<Temperature> factory;

    @Inject
    private SensorService sensorService;

    @GET
    public SensorRepresentation example() {
        SensorRepresentation representation = new SensorRepresentation();
        representation.setSensorId("sensorId");
        representation.setQuantity(factory.create(10, Units.CELSIUS).toString());
        representation.setTime(LocalDateTime.now().toEpochSecond(TIME_ZONE));
        return representation;
    }

    @Path("ids")
    @GET
    public List<String> getSensors() {
        return sensorService.getSensors();
    }

    @Path("/{id}")
    @GET
    public List<SensorRepresentation> getSensors(@PathParam("id") String id) {
        return sensorService.getSensor(id).stream().map(SensorRepresentation::of).collect(toList());
    }

    @POST
    public void save(SensorRepresentation representation) {
        sensorService.save(representation.toSensor(factory));
    }

}
