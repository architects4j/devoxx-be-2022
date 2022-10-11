package org.a4j.presentation.ultrafast;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App {

    public static void main(final String[] args) {

        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            Team team = container.select(Team.class).get();
            Player mario = Player.builder()
                    .name("Mario")
                    .score(10L)
                    .position(Position.ATTACKER)
                    .city("Salvador")
                    .build();

            Player luigi = Player.builder()
                    .name("Luigi")
                    .score(20L)
                    .position(Position.ATTACKER)
                    .city("Lisbon")
                    .build();

            team.save(mario);
            team.save(luigi);

            System.out.println("The players are: " + team.findAll());
            System.out.println("The Mario search: " + team.findById("Mario"));
            team.deleteById("Mario");
            System.out.println("The Sun search: " + team.findById("Mario"));

        }
    }
}
