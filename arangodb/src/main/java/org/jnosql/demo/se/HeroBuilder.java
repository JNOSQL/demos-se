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

import java.util.Collections;
import java.util.Set;

public class HeroBuilder {


    private String name;

    private String realName;

    private Integer age;

    private Set<String> powers = Collections.emptySet();


    public HeroBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder withRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public HeroBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public HeroBuilder withPowers(Set<String> powers) {
        this.powers = powers;
        return this;
    }

    public Hero build() {
        return new Hero(name, realName, age, powers);
    }
}