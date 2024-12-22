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
package org.jnosql.demo.se.programmer;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.jnosql.databases.tinkerpop.mapping.GraphTemplate;

import java.util.Optional;

@ApplicationScoped
public class ProgrammerService {

    private GraphTemplate template;

    public Optional<Programmer> findByName(String name) {
        return template
                .traversalVertex()
                .hasLabel(Programmer.class)
                .has("name", name)
                .next();
    }

    public Programmer save(Programmer programmer) {
        Optional<Programmer> optional = findByName(programmer.getName());
        return optional.map(programmer::merge)
                .map(template::update)
                .orElseGet(() -> this.template.insert(programmer));
    }
}
