package org.jnosql.demo.se;


import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class App2 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            People people = container.select(People.class).get();

            var pageRequest = PageRequest.ofPage(2).size(2);
            Order<Person> order = Order.by(Sort.desc("name"));
            Page<Person> page = people.findAll(pageRequest, order);
            var nextPageable = page.nextPageRequest();
            Page<Person> page2 = people.findAll(nextPageable, order);

        }
    }
}
