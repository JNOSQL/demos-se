/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package org.jnosql.demo.se;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import net.datafaker.Faker;
import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.List;

public class TaskApp {

    public static void main(String[] args) {

        Faker faker = new Faker();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            var tasks = container.select(Tasks.class, DatabaseQualifier.ofDocument()).get();

            tasks.save(Task.newEnabledTask("task 1"));
            tasks.save(Task.newEnabledTask("task 2"));
            tasks.save(Task.newEnabledTask("task 3"));
            tasks.save(Task.newDisabledTask("task 4"));

            List<Task> enabledTasks = tasks.listAllEnabled();
            System.out.println("Enabled tasks:");
            enabledTasks.forEach(System.out::println);

            System.out.println();

            List<Task> disabledTasks = tasks.listAllDisabled();
            System.out.println("Disabled tasks:");
            disabledTasks.forEach(System.out::println);

            tasks.findAll().forEach(tasks::remove);
        }
    }
}
