package org.a4j.presentation.ultrafast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TeamTest {

    private Team team;


    @BeforeEach
    public void setUp() {
        this.team = new Team();
    }

    @Test
    public void shouldAddBody() {
        Assertions.assertTrue(team.isEmpty());
        team.add(Players.MARIO);
        Assertions.assertFalse(team.isEmpty());
        org.assertj.core.api.Assertions.assertThat(team.getPlayers())
                .hasSize(1)
                .contains(Players.MARIO.get());
    }

    @Test
    public void shouldFindByName() {
        team.add(Players.MARIO);
        team.add(Players.LUIGI);

        Optional<Player> player = team.findByName("Mario");
        Assertions.assertTrue(player.isPresent());
        Assertions.assertEquals(Players.MARIO.get(), player.orElseThrow());
        Assertions.assertTrue(team.findByName("unknown").isEmpty());
    }

    @Test
    public void shouldDeleteById() {
        team.add(Players.MARIO.get());
        team.add(Players.LUIGI);

        Optional<Player> player = team.findByName("Mario");
        Assertions.assertTrue(player.isPresent());
        team.deleteByName("Mario");
        Assertions.assertTrue(team.findByName("Mario").isEmpty());
    }

}