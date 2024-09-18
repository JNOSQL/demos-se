package org.jnosql.demo.se.beer;


import jakarta.json.bind.annotation.JsonbVisibility;

@JsonbVisibility(FieldAccessStrategy.class)
public class ElectricEngine extends Engine {

    public ElectricEngine() {
    }

    public ElectricEngine(int horsepower) {
        super(horsepower);
    }

    @Override
    public String getFuelType() {
        return "Electric";
    }

    @Override
    public String toString() {
        return "ElectricEngine{" +
                "horsepower=" + horsepower +
                '}';
    }
}