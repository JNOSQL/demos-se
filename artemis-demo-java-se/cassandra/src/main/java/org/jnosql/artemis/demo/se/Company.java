package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.jnosql.mapping.cassandra.column.UDT;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class Worker {

    @Id
    private String nickname;

    @Column
    @UDT("money")
    private Money salary;

    @Column
    private Set<String> languages;

    @Column
    private Map<String, String> contacts;

    @Column
    private List<> asdf;

}
