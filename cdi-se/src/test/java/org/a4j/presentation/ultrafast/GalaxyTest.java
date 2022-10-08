package org.a4j.presentation.ultrafast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                galaxy.add(null));
    }

    @Test
    public void shouldAddBody() {

    }
}