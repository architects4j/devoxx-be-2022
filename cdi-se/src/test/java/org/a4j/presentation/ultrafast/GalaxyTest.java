package org.a4j.presentation.ultrafast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyTest {

    private Galaxy galaxy;


    @BeforeEach
    public void setUp() {
        this.galaxy = new Galaxy();
    }

    @Test
    public void shouldReturnErrorWhenAddNullBody() {
        Assertions.assertThrows(NullPointerException.class, () ->
                galaxy.add((CelestialBody) null));
    }

    @Test
    public void shouldAddBody() {
        Assertions.assertTrue(galaxy.isEmpty());
        galaxy.add(Bodies.SUN);
        Assertions.assertFalse(galaxy.isEmpty());
        org.assertj.core.api.Assertions.assertThat(galaxy.getBodies())
                .hasSize(1)
                .contains(Bodies.SUN.get());
    }

    @Test
    public void shouldFindByName() {
        galaxy.add(Bodies.SUN);
        galaxy.add(Bodies.EARTH);

        Optional<CelestialBody> body = galaxy.findByName("Sun");
        Assertions.assertTrue(body.isPresent());
        Assertions.assertEquals(Bodies.SUN.get(), body.orElseThrow());
        Assertions.assertTrue(galaxy.findByName("unknown").isEmpty());
    }

    @Test
    public void shouldDeleteById() {
        galaxy.add(Bodies.SUN.get());
        galaxy.add(Bodies.EARTH);

        Optional<CelestialBody> body = galaxy.findByName("Sun");
        Assertions.assertTrue(body.isPresent());
        galaxy.deleteById("Sun");
        Assertions.assertTrue(galaxy.findByName("sun").isEmpty());
    }

}