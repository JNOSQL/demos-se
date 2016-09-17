package org.jnosql.diana.jsr363.gallery;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.util.List;

@Entity("gallery")
public class Gallery {

    static final String DEFAULT_ID = "gallery";

    @Column
    private String id = DEFAULT_ID;

    @Column
    private List<String> images;

    public String getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }
}
