/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package org.jnosql.demo.se;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BasicRepository<Product, String> {
    List<Product> findByName(String name);

    List<Product> findByCategory(String category);

    List<Product> findByMaterial(String material);
}
