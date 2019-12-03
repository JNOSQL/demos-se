package org.jnosql.artemis.demo.se;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@ApplicationScoped
class QuerySupplier implements Supplier<List<String>> {

    private List<String> queries;

    @PostConstruct
    void init() throws IOException {
        final URL resource = QuerySupplier.class.getClassLoader().getResource("cars.nosql");
        this.queries = Files.readAllLines(Paths.get(resource.getFile()));
    }

    @Override
    public List<String> get() {
        return Collections.unmodifiableList(queries);
    }
}
