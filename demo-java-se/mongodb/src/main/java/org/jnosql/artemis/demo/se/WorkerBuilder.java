package org.jnosql.artemis.demo.se;

public class WorkerBuilder {
    private String id;
    private String name;
    private String city;
    private int age;
    private int dailyHours;
    private Gender gender;

    public WorkerBuilder id(String id) {
        this.id = id;
        return this;
    }

    public WorkerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public WorkerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public WorkerBuilder age(int age) {
        this.age = age;
        return this;
    }

    public WorkerBuilder dailyHours(int dailyHours) {
        this.dailyHours = dailyHours;
        return this;
    }

    public WorkerBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Worker build() {
        return new Worker(id, name, city, age, dailyHours, gender);
    }
}