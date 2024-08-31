package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.UUID;

@Entity
public record Task(
        @Id String id,
        @Column String description,
        @Column boolean enabled) {

    public static Task newEnabledTask(String description){
        return new Task(UUID.randomUUID().toString(),description,true);
    }

    public static Task newDisabledTask(String description){
        return new Task(UUID.randomUUID().toString(),description,false);
    }
}
