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

import org.jnosql.artemis.key.KeyValueTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class VillainService {

    @Inject
    private List<String> names;

    @Inject
    private Set<String> powers;

    @Inject
    private KeyValueTemplate template;


    public void addName(String hero) {
        names.add(hero);
    }

    public void addPower(String ids) {
        this.powers.add(ids);
    }

    public void put(Villain villain) {
        template.put(villain);
    }

    public Optional<Villain> get(String name) {
        return template.get(name, Villain.class);
    }

    public List<String> getNames() {
        return names;
    }

    public Set<String> getPowers() {
        return powers;
    }
}
