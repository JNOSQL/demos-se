package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.PreparedStatement;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;
import java.util.UUID;

public class App4 {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Attachment attachment = new Attachment();
            attachment.setId(UUID.randomUUID().toString());
            attachment.setContents(new byte[]{1, 2, 3, 4, 5, 6});
            attachment.setName("file.txt");

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Attachment saved = template.insert(attachment);
            System.out.println("Attachment saved" + saved);

            final String query = "select * from Attachment where _id = @id";
            final PreparedStatement prepare = template.prepare(query);
            prepare.bind("id", attachment.getId());
            final Optional<Attachment> singleResult = prepare.getSingleResult();
            System.out.println(singleResult);

        }
    }
}
