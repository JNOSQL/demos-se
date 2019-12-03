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
package org.jnosql.artemis.demo.se;

import org.eclipse.jnosql.artemis.couchbase.document.CouchbaseRepository;
import org.eclipse.jnosql.artemis.couchbase.document.N1QL;
import org.eclipse.jnosql.artemis.couchbase.document.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface HeroRepository extends CouchbaseRepository<Hero, String> {

    Optional<Hero> findByName(String name);

    Stream<Hero> findByAgeGreaterThan(Integer age);

    Stream<Hero> findByAgeLessThan(Integer age);

    void deleteByName(String name);

    @N1QL("select * from heroes where realName= $realName")
    Optional<Hero> find(@Param("realName") String realName);

}
