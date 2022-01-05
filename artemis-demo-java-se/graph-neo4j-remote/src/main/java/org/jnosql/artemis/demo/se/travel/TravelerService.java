/*
 * Copyright (c) 2021 Otávio Santana and others
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
package org.jnosql.artemis.demo.se.travel;

import org.eclipse.jnosql.mapping.graph.EdgeEntity;
import org.eclipse.jnosql.mapping.graph.GraphTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
class TravelerService {

    private static final String GOAL = "type";
    private static final String FUN = "fun";
    private static final String WORK = "Work";

    @Inject
    private GraphTemplate template;

    public Optional<Traveler> findByName(String name) {
        return template.getTraversalVertex()
                .hasLabel(Traveler.class)
                .has("name", name).next();
    }

    public Traveler findByNameSafe(String name) {
        return template.getTraversalVertex()
                .hasLabel(Traveler.class)
                .has("name", name)
                .<Traveler>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find with name: " + name));
    }

    public Traveler save(Traveler traveler) {
        return findByName(traveler.getName())
                .orElseGet(() -> template.insert(traveler));
    }

    public void load() {
        this.save(Traveler.of("Stark"));
        this.save(Traveler.of("Rogers"));
        this.save(Traveler.of("Romanoff"));
        this.save(Traveler.of("Banners"));
    }

    public void travelFun(Traveler traveler, City city) {
        EdgeEntity edge = template.edge(traveler, Labels.TRAVELS, city);
        edge.add(GOAL, FUN);
    }

    public void travelWork(Traveler traveler, City city) {
        EdgeEntity edge = template.edge(traveler, Labels.TRAVELS, city);
        edge.add(GOAL, WORK);
    }

    public void knows(Traveler travelerA, Traveler travelerB) {
        template.edge(travelerA, Labels.KNOWS, travelerB);
    }
}
