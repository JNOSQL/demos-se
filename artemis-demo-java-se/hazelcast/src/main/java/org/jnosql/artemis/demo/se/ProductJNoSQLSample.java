/*
 * Copyright (c) 2017, 2019 Otávio Santana and others
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
 * Werner Keil
 */

package org.jnosql.artemis.demo.se;

import jakarta.nosql.keyvalue.BucketManagerFactory;
import jakarta.nosql.keyvalue.KeyValueConfiguration;
import org.eclipse.jnosql.diana.hazelcast.keyvalue.HazelcastKeyValueConfiguration;

import java.util.List;

public class ProductJNoSQLSample {

    public static void main(String[] args) {
        KeyValueConfiguration configuration = new HazelcastKeyValueConfiguration();
        BucketManagerFactory bucketManagerFactory = configuration.get();
        List<String> productYear = bucketManagerFactory.getList("product_2018", String.class);
        productYear.add("new Product");
        productYear.add("even newer Product");
        //System.out.println(productYear);
        productYear.forEach(System.out::println);
        System.exit(0);
    }

    private ProductJNoSQLSample() {
    }
}
