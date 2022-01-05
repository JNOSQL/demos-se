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
package org.jnosql.artemis.demo.se.travel;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;
import java.util.Map;

public final class TravelApp {

    private TravelApp() {
    }

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            TravelerService travelerService = container.select(TravelerService.class).get();
            CityService cityService = container.select(CityService.class).get();
            travelerService.load();
            cityService.load();

            Traveler stark = travelerService.findByNameSafe("Stark");
            Traveler roges = travelerService.findByNameSafe("Rogers");
            Traveler romanoff = travelerService.findByNameSafe("Romanoff");
            Traveler banners = travelerService.findByNameSafe("Banners");

            City sanFrancisco = cityService.findByNameSafe("San Francisco");
            City moscow = cityService.findByNameSafe("Moscow");
            City newYork = cityService.findByNameSafe("New York");
            City saoPaulo = cityService.findByNameSafe("São Paulo");
            City casaBlanca = cityService.findByNameSafe("Casa Blanca");

            travelerService.travelFun(stark, sanFrancisco);
            travelerService.travelFun(stark, moscow);
            travelerService.travelFun(stark, newYork);
            travelerService.travelFun(stark, saoPaulo);
            travelerService.travelFun(stark, casaBlanca);

            travelerService.travelWork(roges, newYork);

            travelerService.travelWork(banners, casaBlanca);
            travelerService.travelWork(banners, saoPaulo);


            travelerService.travelWork(romanoff, moscow);
            travelerService.travelWork(romanoff, newYork);
            travelerService.travelWork(romanoff, saoPaulo);
            travelerService.travelFun(romanoff, casaBlanca);

            travelerService.knows(stark, romanoff);
            travelerService.knows(stark, roges);
            travelerService.knows(roges, romanoff);


            Map<String, Long> mostFunCity = cityService.getMostFunCity();
            Map<String, Long> mostBusiness = cityService.getMostBusinessCity();
            Map<String, Long> mostTravelCity = cityService.getMostTravelCity();


            Map<String, Long> peopleHaveFun = travelerService.getPeopleHaveFun();

            Map<String, Long> peopleTravelWork = travelerService.getPeopleTravelWork();

            Map<String, Long> peopleTravel = travelerService.getPeopleTravel();

            List<String> friendsCasaBlanca = travelerService.getFriendsCasaBlanca();

            System.out.println("The city most fun: "+ mostFunCity);
            System.out.println("The city most business: "+ mostBusiness);
            System.out.println("The city with more travel: "+ mostTravelCity);

            System.out.println("The person who traveled fun: "+ peopleHaveFun);
            System.out.println("The person who traveled business: "+ peopleTravelWork);
            System.out.println("The person who traveled: "+ peopleTravel);

            System.out.println("Friends because went to Casa Blanca: " + friendsCasaBlanca);
        }

        System.exit(0);
    }

}
