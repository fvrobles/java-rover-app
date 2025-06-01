package com.aneo.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PlateauTest {
    @Test
    void constructor_whenValidDimensions_shouldCreatePlateau() {
        Plateau plateau = new Plateau(1, 5);
        assertThat(plateau.maxX()).isEqualTo(1);
        assertThat(plateau.maxY()).isEqualTo(5);
    }

    @Test
    void constructor_whenNegativeDimensions_shouldThrowException() {
        try {
            new Plateau(-1, 5);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Plateau dimensions must be positive");
        }

        try {
            new Plateau(5, 0);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Plateau dimensions must be positive");
        }
    }
}
