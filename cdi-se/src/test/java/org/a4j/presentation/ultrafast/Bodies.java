package org.a4j.presentation.ultrafast;

import java.util.function.Supplier;

public enum Bodies implements Supplier<Player> {

    SUN(Player.builder().name("Sun")
            .size(1231231231)
            .type(Position.STAR)
            .habitable(false).build()),
    EARTH(Player.builder().name("Earth")
            .size(1231231231)
            .type(Position.PLANET)
            .habitable(true)
            .build());

    private final Player body;

    Bodies(Player body) {
        this.body = body;
    }

    @Override
    public Player get() {
        return body;
    }
}
