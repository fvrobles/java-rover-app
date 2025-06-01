package com.aneo.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RoverTest {
    
    @Test
    void getPosition_whenRoverIsCreatedAndNoInstruction_shouldReturnInitialPosition() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 2, 'N', plateau);
        
        // When: getting the position of the rover
        String position = rover.getPosition();
        
        // Then: the position should match the expected format
        assertThat(position).isEqualTo("1 2 N");
    }

}
