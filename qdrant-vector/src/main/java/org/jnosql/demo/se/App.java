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

import jakarta.data.Limit;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.vector.Vector;
import org.eclipse.jnosql.mapping.vector.VectorTemplate;

import java.util.List;

/**
 * Pure semantic search. Run {@link InsertApp} first.
 */
public class App {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            VectorTemplate template = container.select(VectorTemplate.class).get();
            TextEmbedder embedder = container.select(TextEmbedder.class).get();

            semanticSearch(template, embedder, "comfortable place for my pet to sleep");
            semanticSearch(template, embedder, "listen to music without cables");
        }
    }

    private static void semanticSearch(VectorTemplate template, TextEmbedder embedder, String query) {
        Vector queryVector = embedder.embed(query);

        List<Product> products = template.search(Product.class)
                .vector(queryVector)
                .limit(Limit.of(3))
                .result();

        System.out.println();
        System.out.println("Semantic query: " + query);
        System.out.println();
        System.out.println("Top matches:");
        int position = 1;
        for (Product product : products) {
            System.out.println(position++ + ". " + product.getName() + " - " + product.getDescription());
        }
    }
}
