package org.jnosql.demo.se;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.Database;
import static org.eclipse.jnosql.mapping.DatabaseType.DOCUMENT;

@ApplicationScoped
public class BaggageSystem {

    @Inject
    @Database(DOCUMENT)
    BaggageRepository baggageRepository;

    public BaggageRepository getBaggageRepository() {
        return baggageRepository;
    }
}
