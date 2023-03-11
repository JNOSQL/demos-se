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
package org.jnosql.demo.se;

import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.nosql.mapping.Repository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends Repository<Driver, String> {

    Optional<Driver> findByName(String name);

    @Query("select * from Driver where cars.plate = @plate")
    Optional<Driver> findByPlate(@Param("plate") String name);

    @Query("select * from Driver where cars.color = @color order by cars.price.value desc")
    List<Driver> findByColor(@Param("color") String color);

    @Query("select * from Driver where cars.model = @model order by cars.price.value desc")
    List<Driver> findByModel(@Param("model") String color);

}
