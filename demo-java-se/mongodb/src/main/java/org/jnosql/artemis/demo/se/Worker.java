package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Convert;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.jnosql.mapping.mongodb.ObjectIdConverter;

import java.util.Objects;

@Entity
public class Worker {

    @Id
    @Convert(ObjectIdConverter.class)
    private String id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private int age;

    @Column
    private int dailyHours;

    Worker() {
    }


    Worker(String id, String name, String city, int age, int dailyHours) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.dailyHours = dailyHours;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public int getDailyHours() {
        return dailyHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", dailyHours=" + dailyHours +
                '}';
    }

    public static WorkerBuilder builder() {
        return new WorkerBuilder();
    }
}
