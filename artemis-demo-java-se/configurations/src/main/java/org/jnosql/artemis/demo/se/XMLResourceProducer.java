/*
 * Copyright (c) 2019 Otávio Santana and others
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
package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.ConfigurationUnit;
import jakarta.nosql.mapping.document.DocumentTemplate;
import jakarta.nosql.document.DocumentCollectionManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class XMLResourceProducer implements ResourcesProducer{

    @Inject
    @ConfigurationUnit(name = "document", database = HEROES, fileName = "jnosql.xml")
    private DocumentCollectionManager manager;

    @Inject
    @ConfigurationUnit(name = "document", database = HEROES, fileName = "jnosql.xml")
    private DocumentTemplate template;

    @Inject
    @ConfigurationUnit(name = "document", database = HEROES, fileName = "jnosql.xml", qualifier = "xml")
    private HeroRepository repository;

    @Override
    public DocumentCollectionManager getDocumentCollectionManager() {
        return manager;
    }

    @Override
    public DocumentTemplate getTemplate() {
        return template;
    }

    @Override
    public HeroRepository getRepository() {
        return repository;
    }
}
