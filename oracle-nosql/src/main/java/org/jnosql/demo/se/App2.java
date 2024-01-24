package org.jnosql.demo.se;


import jakarta.data.page.Page;
import jakarta.data.page.Pageable;

public class App2 {

    public static void main(String[] args) {
        People people = null;

        Page<Person> page = people.findAll(Pageable.ofPage(2).size(2));
        Pageable nextPageable = page.nextPageable();
        Page<Person> page2 = people.findAll(nextPageable);
    }
}
