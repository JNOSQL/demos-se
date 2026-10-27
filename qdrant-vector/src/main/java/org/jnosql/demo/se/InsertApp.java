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
import org.eclipse.jnosql.mapping.vector.Vector;
import org.eclipse.jnosql.mapping.vector.VectorTemplate;

import java.util.List;

/**
 * Seeds the catalog. Run it once, before {@link App} and {@link App2}.
 */
public class InsertApp {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            VectorTemplate template = container.select(VectorTemplate.class).get();
            TextEmbedder embedder = container.select(TextEmbedder.class).get();

            for (Product product : catalog(embedder)) {
                template.insert(product);
                System.out.println("Inserted: " + product.getName());
            }
        }
    }

    private static List<Product> catalog(TextEmbedder embedder) {
        return List.of(
                product(embedder, "1", "Dog Bed",
                        "A soft and comfortable sleeping cushion designed for dogs and household pets to rest.",
                        ProductType.PET),
                product(embedder, "2", "Cat Food",
                        "Dry nutrition formulated for adult cats and household feline pets.",
                        ProductType.PET),
                product(embedder, "3", "Wireless Headphones",
                        "A Bluetooth audio headset for listening to music without cables, with noise cancellation.",
                        ProductType.ELECTRONICS),
                product(embedder, "4", "Bluetooth Speaker",
                        "A portable wireless speaker for playing music and audio without wired connections.",
                        ProductType.ELECTRONICS),
                product(embedder, "5", "Running Shoes",
                        "Lightweight athletic footwear designed for jogging, running, and physical training.",
                        ProductType.SPORTS),
                product(embedder, "6", "Coffee Grinder",
                        "An electric kitchen appliance used to grind roasted coffee beans before brewing.",
                        ProductType.KITCHEN),
                product(embedder, "7", "Laptop Backpack",
                        "A padded carrying bag designed for notebooks, computers, chargers, and accessories.",
                        ProductType.ACCESSORIES));
    }

    private static Product product(TextEmbedder embedder, String id, String name,
                                   String description, ProductType type) {
        // semantic meaning comes from name + description only; the type is not embedded
        String semanticText = """
                Product: %s
                Description: %s
                """.formatted(name, description);

        Vector embedding = embedder.embed(semanticText);

        return new Product(id, name, description, type, embedding);
    }
}
