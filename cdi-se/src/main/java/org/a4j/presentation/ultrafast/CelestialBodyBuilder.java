package org.a4j.presentation.ultrafast;

public class CelestialBodyBuilder {
    private String name;
    private long size;
    private boolean habitable;
    private Position position;

    public CelestialBodyBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CelestialBodyBuilder size(long size) {
        this.size = size;
        return this;
    }

    public CelestialBodyBuilder habitable(boolean habitable) {
        this.habitable = habitable;
        return this;
    }

    public CelestialBodyBuilder type(Position position) {
        this.position = position;
        return this;
    }

    public Player build() {
        return new Player(name, size, habitable, position);
    }
}