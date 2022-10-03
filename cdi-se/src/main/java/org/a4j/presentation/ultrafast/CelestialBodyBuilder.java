package org.a4j.presentation.ultrafast;

public class CelestialBodyBuilder {
    private String name;
    private long size;
    private boolean habitable;
    private Type type;

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

    public CelestialBodyBuilder type(Type type) {
        this.type = type;
        return this;
    }

    public CelestialBody build() {
        return new CelestialBody(name, size, habitable, type);
    }
}