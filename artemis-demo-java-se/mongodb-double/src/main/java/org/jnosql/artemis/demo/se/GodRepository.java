package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface GodRepository extends Repository<God, String> {
}
