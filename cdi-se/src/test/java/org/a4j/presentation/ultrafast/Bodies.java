package org.a4j.presentation.ultrafast;

import java.util.function.Supplier;

public enum Bodies implements Supplier<CelestialBody> {

    SUN(CelestialBody.builder().name("Sun")
            .size(1231231231)
            .type(Type.STAR)
            .habitable(false).build()),
    EARTH(CelestialBody.builder().name("Earth")
            .size(1231231231)
            .type(Type.PLANET)
            .habitable(true)
            .build());

    private final CelestialBody body;

    Bodies(CelestialBody body) {
        this.body = body;
    }

    @Override
    public CelestialBody get() {
        return body;
    }
}
