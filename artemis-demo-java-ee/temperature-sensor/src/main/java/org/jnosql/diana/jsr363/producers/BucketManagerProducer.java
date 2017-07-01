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

package org.jnosql.diana.jsr363.producers;


import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.api.key.KeyValueConfiguration;
import org.jnosql.diana.hazelcast.key.HazelCastKeyValueConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@ApplicationScoped
class BucketManagerProducer {

    private BucketManagerFactory managerFactory;

    private static final Map<String, String> CONFIGURATION = singletonMap("hazelcast-instanceName", "hazelcast");

    @PostConstruct
    public void init() {
        HazelCastKeyValueConfiguration configuration = new HazelCastKeyValueConfiguration();
        managerFactory = configuration.get(CONFIGURATION);
    }

    @PreDestroy
    public void destroy() {
        managerFactory.close();
    }

    @Produces
    public List<String> getList() {
        return managerFactory.getList("sensors", String.class);
    }


}
