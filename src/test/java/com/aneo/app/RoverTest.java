package com.aneo.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RoverTest {

    @Test
    void constructor_whenInvalidPosition_shouldThrowException() {
        // Given: a plateau with valid dimensions
        Plateau plateau = new Plateau(8, 8);
        
        // When: trying to create a rover with an invalid position
        try {
            new Rover(-1, 2, 'N', plateau);
        } catch (IllegalArgumentException e) {
            // Then: an exception should be thrown
            assertThat(e.getMessage()).isEqualTo("Rover position out of bounds");
        }

        try {
            new Rover(1, -2, 'N', plateau);
        } catch (IllegalArgumentException e) {
            // Then: an exception should be thrown
            assertThat(e.getMessage()).isEqualTo("Rover position out of bounds");
        }

        try {
            new Rover(10, 2, 'N', plateau);
        } catch (IllegalArgumentException e) {
            // Then: an exception should be thrown
            assertThat(e.getMessage()).isEqualTo("Rover position out of bounds");
        }

        try {
            new Rover(1, 9, 'N', plateau);
        } catch (IllegalArgumentException e) {
            // Then: an exception should be thrown
            assertThat(e.getMessage()).isEqualTo("Rover position out of bounds");
        }
    }
    
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

    @Test
    void followInstructions_whenMovingLeft_shouldUpdateDirection() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(10, 5);
        Rover rover = new Rover(7, 2, 'N', plateau);
        
        // When: following instructions to turn left
        rover.followInstructions("L");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("7 2 W");

        // When: following instructions to turn left
        rover.followInstructions("L");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("7 2 S");

        // When: following instructions to turn left
        rover.followInstructions("L");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("7 2 E");

        // When: following instructions to turn left
        rover.followInstructions("L");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("7 2 N");
    }

    @Test
    void followInstructions_whenMovingRight_shouldUpdateDirection() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(5, 15);
        Rover rover = new Rover(1, 8, 'N', plateau);
        
        // When: following instructions to turn right
        rover.followInstructions("R");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("1 8 E");

        // When: following instructions to turn right
        rover.followInstructions("R");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("1 8 S");

        // When: following instructions to turn right
        rover.followInstructions("R");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("1 8 W");

        // When: following instructions to turn right
        rover.followInstructions("R");
        
        // Then: the direction should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("1 8 N");
    }

    @Test
    void followInstructions_whenMovingForward_shouldUpdatePosition() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(0, 0, 'N', plateau);
        
        // When: following instructions to move forward
        rover.followInstructions("M");
        
        // Then: the position should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("0 1 N");

        // When: following instructions to move forward again
        rover.followInstructions("M");
        
        // Then: the position should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("0 2 N");
    }

    @Test
    void followInstructions_whenMovingOutsidePlateau_shouldNotChangePosition() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(5, 1);
        Rover rover = new Rover(0, 0, 'E', plateau);
        
        // When: following instructions to move forward out of bounds
        rover.followInstructions("MMMMMMMMMM");
        
        // Then: the position should not change beyond the plateau limits
        assertThat(rover.getPosition()).isEqualTo("5 0 E");
    }

    @Test
    void followInstructions_whenValidInstructions_shouldUpdatePosition() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 2, 'N', plateau);
        
        // When: following instructions
        rover.followInstructions("LMLMLMLMM");
        
        // Then: the position should be updated correctly
        assertThat(rover.getPosition()).isEqualTo("1 3 N");
    }

    @Test
    void followInstructions_whenInvalidInstruction_shouldThrowException() {
        // Given: a valid position and plateau
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 2, 'N', plateau);
        
        // When: following instructions with an invalid instruction
        try {
            rover.followInstructions("LMLMLMLMX");
        } catch (IllegalArgumentException e) {
            // Then: an exception should be thrown
            assertThat(e.getMessage()).isEqualTo("Invalid instruction: X");
        }
    }

}
