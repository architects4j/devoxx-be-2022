package org.a4j.presentation.ultrafast;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface Team extends Repository<Player, String> {

    List<Player> findAll();
}
