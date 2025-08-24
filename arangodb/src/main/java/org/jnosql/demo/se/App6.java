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


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.List;

public class App6 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var repository = container.select(MovieRepository.class, DatabaseQualifier.ofDocument()).get();
            var movie = repository.save(Movie.of("Avengers: Infinity War", 2018));
            var wars = repository.save(Movie.of("Star Wars: The Last Jedi", 2017));
            var star = repository.save(Movie.of("Star Trek Beyond", 2016));
            var found = repository.findById(movie.id());

            System.out.println(found);
            List<Movie> warMovies = repository.findByTitleContains("War");
            System.out.println(warMovies);

            repository.deleteAll(List.of(movie, wars, star));
            repository.deleteAll(repository.findAll().toList());

        }
    }

    private App6() {
    }
}
