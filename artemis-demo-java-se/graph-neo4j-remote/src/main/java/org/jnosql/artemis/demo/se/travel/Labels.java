package org.jnosql.artemis.demo.se.travel;

import java.util.function.Supplier;

public enum Labels implements Supplier<String> {
    TRAVELS("travels"), KNOWS("knows");

    private final String value;

    Labels(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
