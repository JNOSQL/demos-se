/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.jnosql.demo.se;


import jakarta.nosql.Column;

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
        if (!(o instanceof Director director)) return false;
        return Objects.equals(name, director.name) &&
                Objects.equals(movies, director.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, movies);
    }

    @Override
    public String toString() {
        return "Director{" + "name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }

    public static DirectorBuilder builder() {
        return new DirectorBuilder();
    }

    public static class DirectorBuilder {

        private String name;

        private final Set<String> movies = new HashSet<>();

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
