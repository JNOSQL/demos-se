package org.jnosql.artemis.demo.se.mongodb;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import java.util.LinkedList;

@Entity(value = "projects")
public class Project {

    @Id("id")
    private String id;

    @Column
    private String name;

    @Column
    private List<Partner> partners = new LinkedList<>();
}
