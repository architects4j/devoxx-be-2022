package org.a4j.workshop;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.Objects;

@Entity
public class Player {

    @Id
    private String name;

    @Column
    private long score;

    @Column
    private Position position;

    @Column
    private String city;

    @JsonbCreator
    public Player(@JsonbProperty("name") String name,
                  @JsonbProperty("score") long score,
                  @JsonbProperty("position") Position position,
                  @JsonbProperty("city") String city) {
        this.name = name;
        this.score = score;
        this.position = position;
        this.city = city;
    }

    public Player() {
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

}
