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

package org.jnosql.artemis.demo.se.hazelcast;

import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.api.key.KeyValueConfiguration;
import org.jnosql.diana.hazelcast.key.HazelcastKeyValueConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class App3 {

    public static void main(String[] args) {

        KeyValueConfiguration<?> configuration = new HazelcastKeyValueConfiguration();
        BucketManagerFactory<?> managerFactory = configuration.get();
        List<String> products = managerFactory.getList("products", String.class);
        Set<String> uniqueProducts = managerFactory.getSet("unique_products", String.class);
        Queue<String> queue = managerFactory.getQueue("queue", String.class);
        Map<String, String> map = managerFactory.getMap("map", String.class, String.class);
    }

    private App3() {
    }
}

