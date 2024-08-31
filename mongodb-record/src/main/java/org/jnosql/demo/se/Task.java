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

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.UUID;

@Entity
public record Task(
        @Id String id,
        @Column String description,
        @Column boolean enabled) {

    public static Task newEnabledTask(String description){
        return new Task(UUID.randomUUID().toString(),description,true);
    }

    public static Task newDisabledTask(String description){
        return new Task(UUID.randomUUID().toString(),description,false);
    }
}
