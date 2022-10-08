package org.a4j.presentation.ultrafast;

import java.util.Objects;

public class CelestialBody {

    private String name;

    private long size;

    private boolean habitable;

    private Type type;

    CelestialBody(String name, long size, boolean habitable, Type type) {
        this.name = name;
        this.size = size;
        this.habitable = habitable;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public boolean isHabitable() {
        return habitable;
    }

    public Type getType() {
        return type;
    }


    @Override
    public String toString() {
        return "CelestialBody{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", habitable=" + habitable +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CelestialBody body = (CelestialBody) o;
        return Objects.equals(name, body.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static CelestialBodyBuilder builder() {
        return new CelestialBodyBuilder();
    }
}
