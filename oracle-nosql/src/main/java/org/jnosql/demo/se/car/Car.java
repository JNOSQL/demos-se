package org.jnosql.demo.se.car;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import net.datafaker.Faker;
import net.datafaker.providers.base.Vehicle;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Car {


    @Id
    private String id;

    @Column
    private String vin;
    @Column
    private String model;
    @Column
    private String make;
    @Column
    private String transmission;

    public String id() {
        return id;
    }

    public String vin() {
        return vin;
    }

    public String model() {
        return model;
    }

    public String make() {
        return make;
    }

    public String transmission() {
        return transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", transmission='" + transmission + '\'' +
                '}';
    }

    public static Car of(Faker faker){
        Vehicle vehicle = faker.vehicle();
        Car car = new Car();
        car.id = UUID.randomUUID().toString();
        car.vin = vehicle.vin();
        car.model = vehicle.model();
        car.make = vehicle.make();
        car.transmission = vehicle.transmission();
        return car;
    }
}
