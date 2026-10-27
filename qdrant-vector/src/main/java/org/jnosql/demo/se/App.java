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

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.data.Limit;
import org.eclipse.jnosql.mapping.vector.Vector;
import org.eclipse.jnosql.mapping.vector.VectorTemplate;

import java.util.List;

public class App {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            VectorTemplate template = container.select(VectorTemplate.class).get();
            TextEmbedder embedder = container.select(TextEmbedder.class).get();

            for (Product product : catalog(embedder)) {
                template.insert(product);
            }

            // 1. pure semantic search
            semanticSearch(template, embedder, "comfortable place for my pet to sleep");
            semanticSearch(template, embedder, "listen to music without cables");

            // 2. semantic search combined with a regular Jakarta NoSQL condition
            filteredSemanticSearch(template, embedder, "listen to music without cables", ProductType.ELECTRONICS);
            filteredSemanticSearch(template, embedder, "something comfortable for my animal to rest", ProductType.PET);
        }
    }

    private static List<Product> catalog(TextEmbedder embedder) {
        return List.of(
                product(embedder, "1", "Dog Bed", "Soft sleeping cushion for dogs", ProductType.PET),
                product(embedder, "2", "Cat Food", "Dry nutrition for adult cats", ProductType.PET),
                product(embedder, "3", "Wireless Headphones",
                        "Bluetooth audio headset with noise cancellation", ProductType.ELECTRONICS),
                product(embedder, "4", "Bluetooth Speaker",
                        "Portable wireless speaker for listening to music", ProductType.ELECTRONICS),
                product(embedder, "5", "Running Shoes",
                        "Lightweight footwear for jogging and training", ProductType.SPORTS),
                product(embedder, "6", "Coffee Grinder",
                        "Electric machine for grinding coffee beans", ProductType.KITCHEN),
                product(embedder, "7", "Laptop Backpack",
                        "Padded bag for carrying notebooks and accessories", ProductType.ACCESSORIES));
    }

    private static Product product(TextEmbedder embedder, String id, String name,
                                    String description, ProductType type) {
        // only the semantic text is embedded; the type stays a structured field
        Vector embedding = embedder.embed(name + " " + description);
        return new Product(id, name, description, type, embedding);
    }

    private static void semanticSearch(VectorTemplate template, TextEmbedder embedder, String query) {
        Vector queryVector = embedder.embed(query);

        List<Product> products = template.search(Product.class)
                .vector(queryVector)
                .limit(Limit.of(3))
                .result();

        System.out.println();
        System.out.println("Semantic query: " + query);
        print(products);
    }

    private static void filteredSemanticSearch(VectorTemplate template, TextEmbedder embedder,
                                               String query, ProductType type) {
        Vector queryVector = embedder.embed(query);

        List<Product> products = template.search(Product.class)
                .vector(queryVector)
                .where("type").eq(type)
                .limit(Limit.of(3))
                .result();

        System.out.println();
        System.out.println("Semantic query: " + query);
        System.out.println("Filter: type = " + type);
        print(products);
    }

    private static void print(List<Product> products) {
        System.out.println();
        System.out.println("Top matches:");
        int position = 1;
        for (Product product : products) {
            System.out.println(position++ + ". " + product.getName() + " - " + product.getDescription());
        }
    }
}
