package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import net.datafaker.Faker;

@Entity
public record BaggageItem(
        @Column String description,
        @Column Integer weight,
        @Column BaggageItemSize size
) {
    public static BaggageItem of(Faker faker) {
        return new BaggageItem(
                faker.lorem().sentence(faker.random().nextInt(1, 2)),
                faker.random().nextInt(1, 50),
                BaggageItemSize.values()[faker.random().nextInt(0, 2)]);
    }
}
