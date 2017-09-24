/*
 * Copyright (c) 2017 Otávio Santana and others
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

package org.jnosql.artemis.demo.se.couchbase;


import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App3 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Villain lock = new Villain();
            lock.setId("lock");
            lock.setName("Lock");

            VillainCache cache = container.select(VillainCache.class).get();

            cache.addName("Doctor Doom");
            cache.addName("Magneto");
            cache.addName("Red Skull");

            cache.addPower("Strong");
            cache.addPower("Strong");
            cache.addPower("fly");

            cache.put(lock);

            System.out.println(cache.get("lock"));
            System.out.println("The villain powers");
            cache.getPowers().forEach(System.out::println);
            System.out.println("The villain names");
            cache.getNames().forEach(System.out::println);

        }
    }

    private App3() {
    }
}
