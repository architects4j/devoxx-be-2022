package org.a4j.presentation.ultrafast;

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
    public void add(Player player) {
        Objects.requireNonNull(player, "player is not null");
        this.team.add(player);
    }
    @Store
    public void delete(String name) {
        Objects.requireNonNull(name, "name is not null");
        this.team.deleteByName(name);
    }
    public Optional<Player> findById(String name){
        Objects.requireNonNull(name, "name is not null");
        return this.team.findByName(name);
    }

    public String getNames() {
        return this.team.getPlayersName();
    }

    public Set<Player> getPlayers() {
        return this.team.getPlayers();
    }
}
