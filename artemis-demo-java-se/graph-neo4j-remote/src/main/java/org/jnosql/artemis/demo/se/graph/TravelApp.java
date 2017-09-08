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
package org.jnosql.artemis.demo.se.graph;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.graph.GraphTemplate;

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

            Graph thinkerpop = container.select(Graph.class).get();

            load(graph);

            thinkerpop.tx().commit();

            Traveler stark = getTraveler("Stark", graph);
            Traveler roges = getTraveler("Rogers", graph);
            Traveler romanoff = getTraveler("Romanoff", graph);
            Traveler banners = getTraveler("Banners", graph);

            City sanFrancisco = getCity("San Francisco", graph);
            City moscow = getCity("Moscow", graph);
            City newYork = getCity("New York", graph);
            City saoPaulo = getCity("São Paulo", graph);
            City casaBlanca = getCity("Casa Blanca", graph);

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

            thinkerpop.tx().commit();


            Map<String, Long> mostFunCity = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, FUN).inV()
                    .<City>stream()
                    .map(City::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> mostBusiness = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, WORK).inV()
                    .<City>stream()
                    .map(City::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> mostTravelCity = graph.getTraversalVertex()
                    .out(TRAVELS)
                    .<City>stream()
                    .map(City::getName)
                    .collect((groupingBy(Function.identity(), counting())));


            Map<String, Long> personTravelFun = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, FUN).outV()
                    .<Traveler>stream()
                    .map(Traveler::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> personTravelWork = graph.getTraversalVertex()
                    .inE(TRAVELS)
                    .has(GOAL, WORK).outV()
                    .<Traveler>stream()
                    .map(Traveler::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            Map<String, Long> personTravel = graph.getTraversalVertex()
                    .in(TRAVELS)
                    .<Traveler>stream()
                    .map(Traveler::getName)
                    .collect((groupingBy(Function.identity(), counting())));

            List<String> friendsCasaBlanca = graph.getTraversalVertex()
                    .hasLabel("City")
                    .has("name", "Casa Blanca")
                    .in(TRAVELS).<Traveler>stream().map(Traveler::getName).collect(toList());

            System.out.println("The city most fun: "+ mostFunCity);
            System.out.println("The city most business: "+ mostBusiness);
            System.out.println("The city with more travel: "+ mostTravelCity);

            System.out.println("The person who traveled fun: "+ personTravelFun);
            System.out.println("The person who traveled business: "+ personTravelWork);
            System.out.println("The person who traveled: "+ personTravel);


            System.out.println("Friends because went to Casa Blanca: " + casaBlanca);



        }
    }

    private static void load(GraphTemplate graph) {
        graph.insert(Traveler.of("Stark"));
        graph.insert(Traveler.of("Rogers"));
        graph.insert(Traveler.of("Romanoff"));
        graph.insert(Traveler.of("Banners"));

        graph.insert(City.of("San Francisco"));
        graph.insert(City.of("Moscow"));
        graph.insert(City.of("New York"));
        graph.insert(City.of("São Paulo"));
        graph.insert(City.of("Casa Blanca"));
    }

    private static Traveler getTraveler(String name, GraphTemplate graph) {
        return graph.getTraversalVertex().hasLabel("Traveler")
                .has("name", name)
                .<Traveler>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

    private static City getCity(String name, GraphTemplate graph) {
        return graph.getTraversalVertex().hasLabel("City")
                .has("name", name)
                .<City>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }
}
