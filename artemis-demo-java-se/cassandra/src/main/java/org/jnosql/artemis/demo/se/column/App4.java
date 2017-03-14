package org.jnosql.artemis.demo.se.column;


import com.datastax.driver.core.ConsistencyLevel;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Arrays;

public class App4 {


    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer weldContainer = weld.initialize()) {
            MovieRepository repository = weldContainer.instance()
                    .select(MovieRepository.class).get();

            Movie matrix = new Movie();
            matrix.setName("The Matrix");
            matrix.setAge(1999);
            matrix.setDirector(Director.builder().withName("Lana Wachowski")
                    .add("The Matrix").add("The Matrix Reloaded").add("Assassins").build());

            Movie fightClub = new Movie();
            fightClub.setName("Fight Club");
            fightClub.setAge(1999);
            fightClub.setDirector(Director.builder().withName("David Fincher")
                    .add("Fight Club").add("Seven").add("The Social Network").build());

            Movie americanBeuty = new Movie();
            americanBeuty.setName("American Beauty");
            americanBeuty.setAge(1999);
            americanBeuty.setDirector(Director.builder().withName("Sam Mendes")
                    .add("Spectre").add("Skyfall").add("American Beauty").build());


            repository.save(Arrays.asList(matrix, fightClub, americanBeuty), ConsistencyLevel.ONE);

            System.out.println("Movies from 1999: " + repository.findByAge(1999));
            System.out.println("Find all: " + repository.findAll());


        }
    }
}
