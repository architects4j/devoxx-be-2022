package org.a4j.presentation.ultrafast;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbVisibility;
import java.util.Objects;
import org.a4j.presentation.ultrafast.infra.FieldPropertyVisibilityStrategy;

@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Player {
    private final String name;
    private final long score;
    private final Position position;
    private final String city;

    @JsonbCreator
    Player(@JsonbProperty("name") String name,
           @JsonbProperty("score") long score,
           @JsonbProperty("position") Position position,
           @JsonbProperty("city") String city) {
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
