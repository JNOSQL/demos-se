package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.eclipse.jnosql.mapping.cassandra.column.UDT;

@Entity
public class Worker {

    @Id
    private String nickname;

    @Column
    @UDT("money")
    private Money salary;

}
