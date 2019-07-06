package org.jnosql.artemis.demo.se;


import jakarta.nosql.mapping.PreparedStatement;
import jakarta.nosql.mapping.document.DocumentTemplate;
import org.bson.types.Binary;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


public class App5 {


    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Vendor vendor = new Vendor();
            vendor.setName(UUID.randomUUID().toString());
            Binary binary = new Binary(new byte[]{1, 2, 3, 4, 5, 6});
            vendor.setLogo(binary);
            vendor.setLogoLastModified(new Date());
            vendor.setLogoMimeType("picture");

            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Vendor saved = template.insert(vendor);
            System.out.println("Vendor saved" + saved);

            final String query = "select * from Vendor where _id = @name";
            final PreparedStatement prepare = template.prepare(query);
            prepare.bind("name", vendor.getName());
            final Optional<Vendor> singleResult = prepare.getSingleResult();
            System.out.println(singleResult);

        }
    }
}
