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
            var avengers1 = repository.save(Movie.of("The Avengers", 2012));
            var avengers2 = repository.save(Movie.of("Avengers: Age of Ultron", 2015));
            var avengers3 = repository.save(Movie.of("Avengers: Infinity War", 2018));
            var avengers4 = repository.save(Movie.of("Avengers: Endgame", 2019));

            var starWars1 = repository.save(Movie.of("Star Wars: A New Hope", 1977));
            var starWars2 = repository.save(Movie.of("Star Wars: The Empire Strikes Back", 1980));
            var starWars3 = repository.save(Movie.of("Star Wars: Return of the Jedi", 1983));
            var starWars4 = repository.save(Movie.of("Star Wars: The Phantom Menace", 1999));
            var starWars5 = repository.save(Movie.of("Star Wars: Attack of the Clones", 2002));
            var starWars6 = repository.save(Movie.of("Star Wars: Revenge of the Sith", 2005));
            var starWars7 = repository.save(Movie.of("Star Wars: The Force Awakens", 2015));
            var starWars8 = repository.save(Movie.of("Star Wars: The Last Jedi", 2017));
            var starWars9 = repository.save(Movie.of("Star Wars: The Rise of Skywalker", 2019));

            List<Movie> warMovies = repository.findByTitleContains("War");
            List<Movie> startMovies = repository.findByTitleStartsWith("Star");
            List<Movie> jediMovies = repository.findByTitleEndsWith("Jedi");

            System.out.println("War Movies: " + warMovies);
            System.out.println("Start Movies: " + startMovies);
            System.out.println("Jedi Movies: " + jediMovies);
            repository.deleteAll(List.of(avengers1, avengers2, avengers3, avengers4, starWars1, starWars2, starWars3, starWars4, starWars5, starWars6, starWars7, starWars8, starWars9));


        }
    }

    private App6() {
    }
}
