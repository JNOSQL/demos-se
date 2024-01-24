package org.jnosql.demo.se;


import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class App2 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            People people = container.select(People.class).get();;

            Page<Person> page = people.findAll(Pageable.ofPage(2).size(2));
            Pageable nextPageable = page.nextPageable();
            Page<Person> page2 = people.findAll(nextPageable);

        }
    }
}
