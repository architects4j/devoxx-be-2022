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
public class Team {

    private final Set<Player> players = new HashSet<>();

    public void add(Player player) {
        Objects.requireNonNull(player, "player is not null");
        this.players.add(player);
    }

    public void add(Supplier<Player> supplier) {
        Objects.requireNonNull(supplier, "supplier is required");
        add(supplier.get());
    }

    public Optional<Player> findByName(String name) {
        Objects.requireNonNull(name, "name is required");
        return this.players.stream()
                .filter(b -> name.equals(b.getName()))
                .findFirst();
    }

    public void deleteById(String name) {
        Objects.requireNonNull(name, "name is required");
        this.players.removeIf(b -> name.equals(b.getName()));
    }

    public String getPlayersName(){
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(this.players);
    }

    public int size() {
        return this.players.size();
    }

    public boolean isEmpty() {
        return this.players.isEmpty();
    }

    @Override
    public String toString() {
        return "Team{" +
                "players=" + players +
                '}';
    }
}
