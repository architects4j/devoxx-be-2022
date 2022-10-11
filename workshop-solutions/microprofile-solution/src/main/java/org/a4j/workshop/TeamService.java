package org.a4j.workshop;

import one.microstream.integrations.cdi.types.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class TeamService {

    @Inject
    private Team team;
    @Store
    public void add(Player body) {
        Objects.requireNonNull(body, "player is not null");
        this.team.add(body);
    }
    @Store
    public void delete(String name) {

        Objects.requireNonNull(name, "name is required");
        this.team.deleteById(name);
    }
    public Optional<Player> findByName(String name){
        Objects.requireNonNull(name, "name is required");
        return this.team.findByName(name);
    }

    public Set<Player> getPlayers() {
        return this.team.getPlayers();
    }
}
