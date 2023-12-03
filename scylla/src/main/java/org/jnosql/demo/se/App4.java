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

public class App4 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            MovieRepository repository = container.select(MovieRepository.class).get();

            Movie matrix = new Movie();

            matrix.setName("The Matrix");
            matrix.setAge(1999);
            matrix.setDirector(Director.builder().withName("Lana Wachowski")
                    .add("The Matrix").add("The Matrix Reloaded").add("Assassins").build());
            repository.save(matrix);

            Movie fightClub = new Movie();
            fightClub.setName("Fight Club");
            fightClub.setAge(1999);
            fightClub.setDirector(Director.builder().withName("David Fincher")
                    .add("Fight Club").add("Seven").add("The Social Network").build());
            repository.save(fightClub);

            Movie americanBeauty = new Movie();
            americanBeauty.setName("American Beauty");
            americanBeauty.setAge(1999);
            americanBeauty.setDirector(Director.builder().withName("Sam Mendes")
                    .add("Spectre").add("Skyfall").add("American Beauty").build());
            repository.save(americanBeauty);

            System.out.println("Movies from 1999: " + repository.findByAge(1999));
            System.out.println("Find all: " + repository.findAllQuery());


        }
    }

    private App4() {}
}
