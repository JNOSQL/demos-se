package org.jnosql.diana.jsr363;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.measure.Unit;
import javax.measure.quantity.Temperature;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import tec.uom.se.function.QuantityFunctions;
import tec.uom.se.function.QuantitySummaryStatistics;

import static java.util.stream.Collectors.toList;
import static tec.uom.se.unit.Units.CELSIUS;

@ApplicationScoped
class LineChartModelBuilder {

    @Inject
    private SensorService sensorService;

    public LineChartModel createAreaModel(Unit<Temperature> unit) {
        LineChartModel areaModel = new LineChartModel();
        List<Number> numbers = new ArrayList<>();

        for (String sensor : sensorService.getSensors()) {
            List<Sensor> sensors = sensorService.getSensor(sensor);
            Map<String, List<Sensor>> map = sensors.stream()
                    .collect(Collectors.groupingBy(Sensor::getFormatedDate));
            LineChartSeries lineChartSeries = new LineChartSeries();
            lineChartSeries.setLabel(sensor);

            for (String date : map.keySet().stream().sorted().collect(toList())) {
                List<Sensor> sensorsByDate = map.get(date);
                QuantitySummaryStatistics<Temperature> summary = sensorsByDate.stream().map(Sensor::getTemperature).collect(QuantityFunctions.summarizeQuantity(unit));
                Number value = summary.getAverage().getValue();
                lineChartSeries.set(date, value);
                numbers.add(value);
            }
            areaModel.addSeries(lineChartSeries);
        }


        String text = CELSIUS.equals(unit) ? "Celsius" : "Kelvin";
        areaModel.setTitle("Sensors in " + text);
        areaModel.setLegendPosition(text);
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis(text);
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Temperature");
        return areaModel;
    }
}
