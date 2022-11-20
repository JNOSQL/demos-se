/*
 * Copyright (c) 2020 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */
package org.jnosql.demo.se;


import com.hazelcast.core.Hazelcast;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.concurrent.ExecutionException;

public class App2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Hazelcast.newHazelcastInstance();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Animal animal = new Animal("lion");

            AnimalRepository repository = container.select(AnimalRepository.class).get();

            long start = System.currentTimeMillis();
            for (int index = 0; index < 10_000; index++) {
                repository.findById("lion");

            }
            long end = System.currentTimeMillis() - start;
            System.out.println("The process: " + end);
        }
        System.exit(0);
    }


    private App2() {
    }
}
