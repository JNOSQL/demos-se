/*
 * Copyright (c) 2019 Otávio Santana and others
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

package org.jnosql.artemis.demo.se;


import jakarta.nosql.mapping.PreparedStatement;
import jakarta.nosql.mapping.document.DocumentTemplate;
import org.bson.types.Binary;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


public class App5 {


    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Vendor vendor = new Vendor();
            vendor.setName(UUID.randomUUID().toString());
            Binary binary = new Binary(new byte[]{1, 2, 3, 4, 5, 6});
            vendor.setLogo(binary);
            vendor.setLogoLastModified(new Date());
            vendor.setLogoMimeType("picture");

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Vendor saved = template.insert(vendor);
            System.out.println("Vendor saved" + saved);

            final String query = "select * from Vendor where _id = @name";
            final PreparedStatement prepare = template.prepare(query);
            prepare.bind("name", vendor.getName());
            final Optional<Vendor> singleResult = prepare.getSingleResult();
            System.out.println(singleResult);

        }
    }

    private App5() {
    }
}
