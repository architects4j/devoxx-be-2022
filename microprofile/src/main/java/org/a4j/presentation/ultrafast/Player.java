package org.a4j.presentation.ultrafast;

import java.util.Objects;

public class Player {
    private final String name;
    private final long score;
    private final Position position;
    private final String city;
    Player(String name, long score, Position position, String city) {
        this.name = name;
        this.score = score;
        this.position = position;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    public Position getPosition() {
        return position;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return score == player.score
                && Objects.equals(name, player.name)
                && position == player.position
                && Objects.equals(city, player.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, position, city);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", position=" + position +
                ", city='" + city + '\'' +
                '}';
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }
}
