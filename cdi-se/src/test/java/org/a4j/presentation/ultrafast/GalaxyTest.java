package org.a4j.presentation.ultrafast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyTest {

    private Galaxy galaxy;

    private CelestialBody sun = CelestialBody.builder().name("Sun")
            .size(1231231231)
            .type(Type.STAR)
            .habitable(false).build();


    private CelestialBody earth = CelestialBody.builder().name("Earth")
            .size(1231231231)
            .type(Type.PLANET)
            .habitable(true)
            .build();

    @BeforeEach
    public void setUp() {
        this.galaxy = new Galaxy();
    }

    @Test
    public void shouldReturnErrorWhenAddNullBody() {
        Assertions.assertThrows(NullPointerException.class, () ->
                galaxy.add(null));
    }

    @Test
    public void shouldAddBody() {
        Assertions.assertTrue(galaxy.isEmpty());
        galaxy.add(sun);
        Assertions.assertFalse(galaxy.isEmpty());
    }
}