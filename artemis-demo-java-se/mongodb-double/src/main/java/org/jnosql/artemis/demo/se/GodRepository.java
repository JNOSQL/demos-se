package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface GodRepository extends Repository<God, String> {

    List<God> findByName(String name);
}
