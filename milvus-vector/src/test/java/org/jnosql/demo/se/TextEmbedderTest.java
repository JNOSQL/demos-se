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

import org.eclipse.jnosql.mapping.vector.DenseVector;
import org.eclipse.jnosql.mapping.vector.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextEmbedderTest {

    private final TextEmbedder embedder = new TextEmbedder();

    @Test
    void shouldCreateDenseVectorFromText() {
        Vector vector = embedder.embed("Dog Bed Soft sleeping cushion for dogs");

        Assertions.assertInstanceOf(DenseVector.class, vector);
        Assertions.assertEquals(TextEmbedder.DIMENSIONS, ((DenseVector) vector).dimensions());
    }

    @Test
    void shouldPlaceRelatedTextCloserThanUnrelatedText() {
        float[] pet = ((DenseVector) embedder.embed("comfortable place for my pet to sleep")).values();
        float[] bed = ((DenseVector) embedder.embed("Dog Bed Soft sleeping cushion for dogs")).values();
        float[] grinder = ((DenseVector) embedder.embed("Coffee Grinder Electric machine for grinding coffee beans")).values();

        Assertions.assertTrue(dot(pet, bed) > dot(pet, grinder));
    }

    // embeddings are normalized, so the dot product equals the cosine similarity
    private static double dot(float[] a, float[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
