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
    public void shouldReturnErrorWhenAddNullBody() {
        Assertions.assertThrows(NullPointerException.class, () ->
                team.add((CelestialBody) null));
    }

    @Test
    public void shouldAddBody() {
        Assertions.assertTrue(team.isEmpty());
        team.add(Bodies.SUN);
        Assertions.assertFalse(team.isEmpty());
        org.assertj.core.api.Assertions.assertThat(team.getBodies())
                .hasSize(1)
                .contains(Bodies.SUN.get());
    }

    @Test
    public void shouldFindByName() {
        team.add(Bodies.SUN);
        team.add(Bodies.EARTH);

        Optional<CelestialBody> body = team.findByName("Sun");
        Assertions.assertTrue(body.isPresent());
        Assertions.assertEquals(Bodies.SUN.get(), body.orElseThrow());
        Assertions.assertTrue(team.findByName("unknown").isEmpty());
    }

    @Test
    public void shouldDeleteById() {
        team.add(Bodies.SUN.get());
        team.add(Bodies.EARTH);

        Optional<CelestialBody> body = team.findByName("Sun");
        Assertions.assertTrue(body.isPresent());
        team.deleteById("Sun");
        Assertions.assertTrue(team.findByName("sun").isEmpty());
    }

}