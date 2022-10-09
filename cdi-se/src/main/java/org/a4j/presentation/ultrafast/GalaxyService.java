package org.a4j.presentation.ultrafast;

import one.microstream.integrations.cdi.types.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class GalaxyService {

    @Inject
    private Team team;

    @Store
    public void add(CelestialBody body) {
        this.team.add(body);
    }

    @Store
    public void delete(String name) {
        this.team.deleteById(name);
    }

    public Optional<CelestialBody> findById(String name){
        return this.team.findByName(name);
    }

    public String getNames() {
        return this.team.getCelestialNames();
    }

    public Set<CelestialBody> getBodies() {
        return this.team.getBodies();
    }
}
