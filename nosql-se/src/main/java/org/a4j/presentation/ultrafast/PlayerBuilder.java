package org.a4j.presentation.ultrafast;

public class PlayerBuilder {
    private String name;
    private long score;
    private Position position;
    private String city;

    public PlayerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder score(long score) {
        this.score = score;
        return this;
    }

    public PlayerBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public PlayerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public Player build() {
        return new Player(name, score, position, city);
    }
}