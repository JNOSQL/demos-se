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

import com.hazelcast.cache.HazelcastExpiryPolicy;

import javax.cache.configuration.Factory;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ExpiryPolicyFactory implements Factory<ExpiryPolicy> {

    private Duration create;
    private Duration access;
    private Duration update;

    public ExpiryPolicyFactory() {
        this.create = new Duration(SECONDS, 2);
        this.access = new Duration(SECONDS, 2);
        this.update = new Duration(SECONDS, 2);
    }

    @Override
    public ExpiryPolicy create() {
        return new HazelcastExpiryPolicy(create, access, update);
    }
}