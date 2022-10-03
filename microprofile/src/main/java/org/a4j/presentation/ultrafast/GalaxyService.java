package org.a4j.presentation.ultrafast;

import one.microstream.integrations.cdi.types.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class GalaxyService {

    @Inject
    private Galaxy galaxy;

    @Store
    public void add(CelestialBody body) {
        this.galaxy.add(body);
    }

    @Store
    public void delete(String name) {
        this.galaxy.deleteById(name);
    }

    public Optional<CelestialBody> findById(String name){
        return this.galaxy.findByName(name);
    }

    public String getNames() {
        return this.galaxy.getCelestialNames();
    }

    public Set<CelestialBody> getBodies() {
        return this.galaxy.getBodies();
    }
}
