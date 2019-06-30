package org.jnosql.artemis.demo.se;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import java.util.Arrays;

@Entity
public class Attachment {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private byte[] contents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }
}