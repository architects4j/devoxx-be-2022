package org.a4j.presentation.ultrafast;

import one.microstream.integrations.cdi.types.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class TeamService {

    @Inject
    private Team team;
    @Store
    public void add(Player body) {
        this.team.add(body);
    }
    @Store
    public void delete(String name) {
        this.team.deleteById(name);
    }
    public Optional<Player> findById(String name){
        return this.team.findByName(name);
    }

    public String getNames() {
        return this.team.getPlayersName();
    }

    public Set<Player> getPlayers() {
        return this.team.getPlayers();
    }
}
