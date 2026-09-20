/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
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

import java.time.Instant;

public class App {

    public static void main(String[] args) {

        var firstReading = new SensorReading(
                Instant.now(),
                "sensor-01",
                21.4,
                45.0
        );

        var secondReading = new SensorReading(
                Instant.now(),
                "sensor-01",
                22.1,
                46.5
        );

        var latestReading = new SensorReading(
                Instant.now(),
                "sensor-01",
                23.6,
                48.2
        );

        try (SeContainer container =
                     SeContainerInitializer.newInstance().initialize()) {

            TimeSeriesTemplate template =
                    container.select(TimeSeriesTemplate.class).get();

            template.insert(firstReading);
            template.insert(secondReading);
            template.insert(latestReading);

            var currentReading = template
                    .select(SensorReading.class)
                    .where("sensor")
                    .eq("sensor-01")
                    .orderBy("id")
                    .desc()
                    .limit(1)
                    .singleResult();

            System.out.println(
                    "Current sensor reading: " + currentReading
            );

            var history = template
                    .select(SensorReading.class)
                    .where("sensor")
                    .eq("sensor-01")
                    .orderBy("id")
                    .desc()
                    .skip(1)
                    .limit(10)
                    .result();

            System.out.println("Recent sensor history:");
            history.forEach(System.out::println);
        }
    }

    private App() {
    }
}
