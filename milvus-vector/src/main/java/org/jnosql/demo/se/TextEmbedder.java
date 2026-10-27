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

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.jnosql.mapping.vector.DenseVector;
import org.eclipse.jnosql.mapping.vector.Vector;

import java.util.Objects;

/**
 * Converts text into an Eclipse JNoSQL {@link Vector} using a local, in-process
 * ONNX model. No external service or API key is involved.
 */
@ApplicationScoped
public class TextEmbedder {

    /** Number of dimensions produced by all-MiniLM-L6-v2. */
    public static final int DIMENSIONS = 384;

    private final EmbeddingModel model = new AllMiniLmL6V2QuantizedEmbeddingModel();

    public Vector embed(String text) {
        Objects.requireNonNull(text, "text is required");
        float[] values = model.embed(text).content().vector();
        return DenseVector.of(values);
    }
}
