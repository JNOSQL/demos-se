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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.document.DocumentTemplate;

import java.util.stream.Stream;

import static org.eclipse.jnosql.communication.semistructured.SelectQuery.select;

@ApplicationScoped
public class GreekService {

    @Inject
    private DocumentTemplate template;

    public God insert(God god) {
        return template.insert(god);
    }

    public Stream<God> findName(String name) {
        var query = select().from("God")
                .where("name")
                .eq(name).build();
        return template.select(God.class) .where("name")
                .eq(name).stream();
    }
}
