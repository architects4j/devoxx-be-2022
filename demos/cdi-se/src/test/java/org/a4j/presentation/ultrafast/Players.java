package org.a4j.presentation.ultrafast;

import java.util.function.Supplier;

import static org.a4j.presentation.ultrafast.Player.builder;

public enum Players implements Supplier<Player> {

    MARIO(builder()
            .name("Mario")
            .score(10L)
            .position(Position.ATTACKER)
            .city("Salvador")
            .build()),
    LUIGI(builder()
            .name("Luigi")
            .score(20L)
            .position(Position.ATTACKER)
            .city("Lisbon")
            .build());

    private final Player body;

    Players(Player body) {
        this.body = body;
    }

    @Override
    public Player get() {
        return body;
    }
}
