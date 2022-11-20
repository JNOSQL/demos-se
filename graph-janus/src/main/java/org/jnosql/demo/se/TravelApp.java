/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */
package org.jnosql.demo.se;

import org.eclipse.jnosql.mapping.graph.GraphTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public final class TravelApp {

    private TravelApp() {
    }

    private static final String GOAL = "type";
    private static final String FUN = "fun";
    private static final String TRAVELS = "travels";
    private static final String WORK = "Work";

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate graph = container.select(GraphTemplate.class).get();

            Traveler stark = graph.insert(Traveler.of("Stark"));
            Traveler roges = graph.insert(Traveler.of("Rogers"));
            Traveler romanoff = graph.insert(Traveler.of("Romanoff"));
            Traveler banners = graph.insert(Traveler.of("Banners"));

            City sanFrancisco = graph.insert(City.of("San Francisco"));
            City moscow = graph.insert(City.of("Moscow"));
            City newYork = graph.insert(City.of("New York"));
            City saoPaulo = graph.insert(City.of("São Paulo"));
            City casaBlanca = graph.insert(City.of("Casa Blanca"));

            graph.edge(stark, TRAVELS, sanFrancisco).add(GOAL, FUN);
            graph.edge(stark, TRAVELS, moscow).add(GOAL, FUN);
            graph.edge(stark, TRAVELS, newYork).add(GOAL, FUN);
            graph.edge(stark, TRAVELS, saoPaulo).add(GOAL, FUN);
            graph.edge(stark, TRAVELS, casaBlanca).add(GOAL, FUN);

            graph.edge(roges, TRAVELS, newYork).add(GOAL, WORK);

            graph.edge(banners, TRAVELS, casaBlanca).add(GOAL, WORK);
            graph.edge(banners, TRAVELS, saoPaulo).add(GOAL, WORK);

            graph.edge(romanoff, TRAVELS, moscow).add(GOAL, WORK);
            graph.edge(romanoff, TRAVELS, newYork).add(GOAL, WORK);
            graph.edge(romanoff, TRAVELS, saoPaulo).add(GOAL, WORK);
            graph.edge(romanoff, TRAVELS, casaBlanca).add(GOAL, FUN);

            graph.edge(stark, "knows", romanoff);
            graph.edge(stark, "knows", roges);
            graph.edge(roges, "knows", romanoff);



            Map<String, Long> mostFunCity = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, FUN).inV()
                    .<City>getResult()
                    .map(City::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> mostBusiness = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, WORK).inV()
                    .<City>getResult()
                    .map(City::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> mostTravelCity = graph.getTraversalVertex()
                    .out(TRAVELS)
                    .<City>getResult()
                    .map(City::getName)
                    .collect((groupingBy(Function.identity(), counting())));


            Map<String, Long> personTravelFun = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, FUN).outV()
                    .<Traveler>getResult()
                    .map(Traveler::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> personTravelWork = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, WORK).outV()
                    .<Traveler>getResult()
                    .map(Traveler::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> personTravel = graph.getTraversalVertex()
                    .in(TRAVELS)
                    .<Traveler>getResult()
                    .map(Traveler::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            List<String> friendsCasaBlanca = graph.getTraversalVertex()
                    .hasLabel("City")
                    .has("name", "Casa Blanca")
                    .in(TRAVELS).<Traveler>getResult().map(Traveler::getName).collect(toList());

            System.out.println("The city most fun: "+ mostFunCity);
            System.out.println("The city most business: "+ mostBusiness);
            System.out.println("The city with more travel: "+ mostTravelCity);

            System.out.println("The person who traveled fun: "+ personTravelFun);
            System.out.println("The person who traveled business: "+ personTravelWork);
            System.out.println("The person who traveled: "+ personTravel);


            System.out.println("Friends because went to Casa Blanca: " + casaBlanca);



        }
    }
}
