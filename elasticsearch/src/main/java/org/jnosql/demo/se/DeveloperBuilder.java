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

import java.util.ArrayList;
import java.util.List;


public class DeveloperBuilder {

    private long id;

    private String name;

    private List<String> phones;

    private Address address;

    private final List<String> languages = new ArrayList<>();

    public DeveloperBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public DeveloperBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public DeveloperBuilder withPhones(List<String> phones) {
        this.phones = phones;
        return this;
    }

    public DeveloperBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public DeveloperBuilder withLanguage(String language) {
       this.languages.add(language);
       return this;
    }



    public Developer build() {
        return new Developer(id, name, phones, languages, address);
    }
}
