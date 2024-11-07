package org.jnosql.demo.se.beer;

import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;

@Embeddable
public record Guest (@Column String documentNumber, @Column String name) {
}
