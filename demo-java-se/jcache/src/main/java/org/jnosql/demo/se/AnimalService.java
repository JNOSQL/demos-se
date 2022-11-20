/*
 * Copyright (c) 2020 Otávio Santana and others
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

import javax.cache.annotation.CacheResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class AnimalService {

    private static final Logger LOGGER = Logger.getLogger(AnimalService.class.getName());

    @Inject
    private AnimalRepository repository;

    public void save(Animal animal) {
        repository.save(animal);
    }

    @CacheResult(cacheName = "animalService")
    public Animal findById(String id) {
        LOGGER.info("Loading information from database: " + id);
        return repository.findById(id).get();
    }
}
