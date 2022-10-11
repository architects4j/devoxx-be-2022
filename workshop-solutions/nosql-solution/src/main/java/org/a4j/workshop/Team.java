package org.a4j.workshop;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface Team extends Repository<Player, String> {

    List<Player> findAll();
}
