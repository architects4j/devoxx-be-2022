package org.a4j.presentation.ultrafast;

import one.microstream.integrations.cdi.types.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Storage
public class Galaxy {

    private final Set<CelestialBody> bodies = new HashSet<>();

    public void add(CelestialBody body) {
        Objects.requireNonNull(body, "body is not null");
        this.bodies.add(body);
    }

    public void add(Supplier<CelestialBody> supplier) {
        Objects.requireNonNull(supplier, "supplier is required");
        add(supplier.get());
    }

    public Optional<CelestialBody> findByName(String name) {
        Objects.requireNonNull(name, "name is required");
        return this.bodies.stream()
                .filter(b -> name.equals(b.getName()))
                .findFirst();
    }

    public void deleteById(String name) {
        Objects.requireNonNull(name, "name is required");
        this.bodies.removeIf(b -> name.equals(b.getName()));
    }

    public String getCelestialNames(){
        return bodies.stream()
                .map(CelestialBody::getName)
                .collect(Collectors.joining(","));
    }

    public Set<CelestialBody> getBodies() {
        return Collections.unmodifiableSet(this.bodies);
    }

    public int size() {
        return this.bodies.size();
    }

    public boolean isEmpty() {
        return this.bodies.isEmpty();
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "bodies=" + bodies +
                '}';
    }
}
