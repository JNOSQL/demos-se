package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import net.datafaker.Faker;

import java.util.stream.IntStream;

@Entity
public record Baggage(
        @Id String ticket,
        @Column BaggageItem[] items
) {
    public static Baggage create(Faker faker) {
        return new Baggage(
                faker.code().isbn10(),
                IntStream.rangeClosed(0, faker.random().nextInt(1, 5))
                        .mapToObj(i -> BaggageItem.of(faker))
                        .toArray(BaggageItem[]::new)
        );
    }
}
