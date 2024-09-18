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
import org.eclipse.jnosql.mapping.document.DocumentTemplate;

import java.util.List;
import java.util.Map;

public class App5 {


    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
          Form form = new Form();
          form.setId("form");
          form.setOption("option_1");
          form.setQuestions(Map.of("question_1", "Duke", "question_2", false, "question_3",
                  Map.of("question_3_1", "Java", "question_3_2", "Jakarta EE")));

            var template = container.select(DocumentTemplate.class).get();
            template.delete(Form.class).execute();
            template.insert(form);
            List<Form> forms = template.select(Form.class).result();
            System.out.println(forms);

        }
    }

    private App5() {
    }
}
