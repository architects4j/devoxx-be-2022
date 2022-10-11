package org.a4j.workshop;

import one.microstream.integrations.cdi.types.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// Annotate the team, our root element, with @Storage
@Storage
public class Team {

    private final Set<Player> players = new HashSet<>();

    public void add(Player player) {
        // add new player to the Set of players
        this.players.add(player);
    }

    public Optional<Player> findByName(String name) {
        // use Java streams to find the player that has the input name
        return this.players.stream()
                .filter(b -> name.equals(b.getName()))
                .findFirst();
    }

    public void deleteById(String name) {
        // Remove the object with the received name from the list of players using native Java APIs (e.g. streams)
        this.players.removeIf(b -> name.equals(b.getName()));
    }

    public Set<Player> getPlayers() {
        // use Java streams to return all players in the set
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
