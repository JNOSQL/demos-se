/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.jnosql.demo.se;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.timeseries.TimeSeriesTemplate;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Demonstrates the temporal operations of the time-series fluent query API.
 * <p>
 * The temporal reference of every query is the entity attribute annotated with {@code @Id}
 * ({@code SensorReading.id}, an {@link Instant}).
 * <ul>
 *     <li>{@code latest(n)}: the {@code n} newest observations</li>
 *     <li>{@code earliest(n)}: the {@code n} oldest observations</li>
 *     <li>{@code since(t)}: observations at or after {@code t}</li>
 *     <li>{@code until(t)}: observations at or before {@code t}</li>
 *     <li>{@code last(d)}: observations within the last {@code d}, relative to the execution time</li>
 * </ul>
 * The sample data is generated relative to the execution time, so the results are comparable on every run.
 */
public class TemporalQueryApp {

    static final String SENSOR = "sensor-temporal-01";
    static final String SENSOR_OTHER = "sensor-temporal-02";

    /**
     * Age, in hours, of the {@link #SENSOR} observations: 72h, 48h, 30h, 20h, 10h and 2h old.
     * Only the last three fall inside {@code last(Duration.ofHours(24))}.
     */
    static final List<Long> AGES_IN_HOURS = List.of(72L, 48L, 30L, 20L, 10L, 2L);

    public static void main(String[] args) {

        try (SeContainer container =
                     SeContainerInitializer.newInstance().initialize()) {

            TimeSeriesTemplate template =
                    container.select(TimeSeriesTemplate.class).get();

            var now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
            seed(template, now, SENSOR, SENSOR_OTHER);

            var start = now.minus(Duration.ofHours(36));
            var end = now.minus(Duration.ofHours(12));

            // 1. latest: the three newest observations
            print("latest(3)", template
                    .select(SensorReading.class)
                    .latest(3));

            // 2. earliest: the three oldest observations
            print("earliest(3)", template
                    .select(SensorReading.class)
                    .earliest(3));

            // 3. since: observations from a timestamp
            print("since(" + start + ")", template
                    .select(SensorReading.class)
                    .since(start)
                    .result());

            // 4. until: observations up to a timestamp
            print("until(" + end + ")", template
                    .select(SensorReading.class)
                    .until(end)
                    .result());

            // 5. last: observations within a relative period
            print("last(24h)", template
                    .select(SensorReading.class)
                    .last(Duration.ofHours(24))
                    .result());

            // 6. composing: relative period + terminal retrieval
            print("last(24h).latest(2)", template
                    .select(SensorReading.class)
                    .last(Duration.ofHours(24))
                    .latest(2));

            // 7. composing: where + since + until + terminal retrieval
            print("where(sensor = " + SENSOR + ").since(start).until(end).latest(3)", template
                    .select(SensorReading.class)
                    .where("sensor").eq(SENSOR)
                    .since(start)
                    .until(end)
                    .latest(3));

            // 8. composing: where + last
            print("where(sensor = " + SENSOR + ").last(24h).earliest(1)", template
                    .select(SensorReading.class)
                    .where("sensor").eq(SENSOR)
                    .last(Duration.ofHours(24))
                    .earliest(1));
        }
    }

    /**
     * Inserts the deterministic sample data, calculated relative to {@code now}:
     * six observations of {@code sensor} and one of {@code other}, one hour old.
     */
    static void seed(TimeSeriesTemplate template, Instant now, String sensor, String other) {
        for (int index = 0; index < AGES_IN_HOURS.size(); index++) {
            var timestamp = now.minus(Duration.ofHours(AGES_IN_HOURS.get(index)));
            template.insert(new SensorReading(timestamp, sensor, 20.0 + index, 40.0 + index));
        }
        template.insert(new SensorReading(now.minus(Duration.ofHours(1)), other, 30.0, 60.0));
    }

    private static void print(String title, List<SensorReading> observations) {
        System.out.println(title + ":");
        observations.forEach(observation -> System.out.println("  " + observation));
    }

    private TemporalQueryApp() {
    }
}
