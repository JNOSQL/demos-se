package org.jnosql.diana.jsr363.gallery;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import java.io.Serializable;
import java.util.Objects;

@Entity("image")
public class Image implements Serializable {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String title;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return false;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return Objects.equals(name, image.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
