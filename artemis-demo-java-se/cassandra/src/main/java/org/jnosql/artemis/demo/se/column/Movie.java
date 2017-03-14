package org.jnosql.artemis.demo.se.column;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.cassandra.column.UDT;

import java.util.Objects;

@Entity
public class Movie {

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    @UDT("director")
    private Director director;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", director=").append(director);
        sb.append('}');
        return sb.toString();
    }
}
