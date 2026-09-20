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


import jakarta.data.Limit;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.time.Instant;

public class App2 {

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

            SensorReadingRepository repository =
                    container.select(SensorReadingRepository.class).get();

            repository.save(firstReading);
            repository.save(secondReading);
            repository.save(latestReading);

            var currentReading = repository
                    .findBySensorOrderByIdDesc(
                            "sensor-01",
                            Limit.of(1)
                    )
                    .stream()
                    .findFirst();

            System.out.println(
                    "Current sensor reading: " + currentReading
            );

            var history = repository
                    .findBySensorOrderByIdDesc(
                            "sensor-01",
                            Limit.range(2, 10)
                    );

            System.out.println("Recent sensor history:");
            history.forEach(System.out::println);
        }
    }

    private App2() {
    }
}
