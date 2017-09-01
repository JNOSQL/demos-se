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
package org.jnosql.artemis.demo;


import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.GraphTemplate;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@ManagedBean(name = "diagramBasicView")
@RequestScoped
public class BasicView {

    private DefaultDiagramModel model;

    private AtomicInteger counterX = new AtomicInteger(10);
    private AtomicInteger counterY = new AtomicInteger(6);
    private AtomicInteger elementCounter = new AtomicInteger(1);

    @Inject
    private GraphTemplate graph;

    private Map<NameableElement, Element> elements = new HashMap<>();

    @PostConstruct
    public void init() {

        load();
        List<EdgeEntity<Nameable, Nameable>> edgeEntities = graph.getTraversalEdge()
                .<Nameable, Nameable>stream().collect(toList());

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        for (EdgeEntity<Nameable, Nameable> edge : edgeEntities) {
            Element inbound = get(edge.getInbound());
            Element outBound = get(edge.getOutbound());

            model.connect(new Connection(inbound.getEndPoints().get(0), outBound.getEndPoints().get(0)));
        }

    }

    private Element get(Nameable nameable) {
        NameableElement nameableElement = new NameableElement(nameable);
        Element element = elements.get(nameableElement);
        if (Objects.isNull(element)) {
            element = new Element(nameable.getName(), counterX.get() + "em", counterY.get() + "em");
            element.addEndPoint(new DotEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elements.put(nameableElement, element);
            model.addElement(element);
            counterX.getAndAdd(15);
            elementCounter.getAndAdd(1);
            if (elementCounter.get() % 3 == 0) {
                counterY.getAndAdd(12);
                counterX.set(10);
            }

        }

        return element;
    }

    private void load() {
        Category software = graph.insert(Category.of("Software"));

        Category java = graph.insert(Category.of("Java"));
        Category nosql = graph.insert(Category.of("NoSQL"));
        Category microService = graph.insert(Category.of("Micro Service"));

        Book effectiveJava = graph.insert(Book.of("Effective Java"));
        Book nosqlDistilled = graph.insert(Book.of("NoSQL D"));
        Book migratingMicroservice = graph.insert(Book.of("Migrating..."));

        graph.edge(java, "is", software);
        graph.edge(nosql, "is", software);
        graph.edge(microService, "is", software);


        graph.edge(effectiveJava, "is", java);
        graph.edge(nosqlDistilled, "is", nosql);
        graph.edge(migratingMicroservice, "is", microService);
    }

    public DiagramModel getModel() {
        return model;
    }
}