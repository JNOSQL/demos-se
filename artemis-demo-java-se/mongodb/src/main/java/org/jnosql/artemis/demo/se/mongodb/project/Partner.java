package org.jnosql.artemis.demo.se.mongodb;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Id;

public class Partner {

    @Id("id")
    private String id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String type; // TODO: Add an enum

    @Column
    private String role;

    @Column
    private Address location;
}
