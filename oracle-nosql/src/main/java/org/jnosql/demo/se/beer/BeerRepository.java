/*
 *  Copyright (c) 2024 Contributors to the Eclipse Foundation
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.jnosql.demo.se.beer;

import java.util.List;
import java.util.Set;

import jakarta.data.page.CursoredPage;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.Find;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import org.eclipse.jnosql.databases.oracle.mapping.OracleNoSQLRepository;

@Repository
public interface BeerRepository extends OracleNoSQLRepository<Beer, String> {

    Set<Beer> findByStyle(String style);

    @Query("select * from Beer")
    Set<Beer> query();

    @Find
    @OrderBy("hop")
    CursoredPage<Beer> style(@By("style") String style, PageRequest pageRequest);

    @Query("From Beer where style = ?1")
    List<Beer> jpql(String style);
}