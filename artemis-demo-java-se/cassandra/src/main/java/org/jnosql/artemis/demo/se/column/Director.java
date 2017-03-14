package org.jnosql.artemis.demo.se.column;


import org.jnosql.artemis.Column;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Director {

    @Column
    private String name;

    @Column
    private Set<String> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMovies() {
        return movies;
    }

    public void setMovies(Set<String> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director)) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name) &&
                Objects.equals(movies, director.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, movies);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Director{");
        sb.append("name='").append(name).append('\'');
        sb.append(", movies=").append(movies);
        sb.append('}');
        return sb.toString();
    }

    public static DirectorBuilder builder() {
        return new DirectorBuilder();
    }

    public static class DirectorBuilder {

        private String name;

        private Set<String> movies = new HashSet<>();

        public DirectorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DirectorBuilder add(String movie) {
            this.movies.add(movie);
            return this;
        }

        public Director build() {
            Director director = new Director();
            director.setName(name);
            director.setMovies(movies);
            return director;
        }
    }
}
