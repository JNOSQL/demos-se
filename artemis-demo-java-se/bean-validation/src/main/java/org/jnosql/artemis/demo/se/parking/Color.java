package org.jnosql.artemis.demo.se.parking;

import java.util.function.Supplier;

public enum  Color implements Supplier<String> {

    BLACK("Black"), WHITE("White"), RED("Red"), BLUE("Blue");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
