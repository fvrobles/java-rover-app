package com.aneo.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    void navigate_whenInputFileIsValid_shouldProduceExpectedOutput() throws Exception {
        // Given: a valid input file
        Path inputFile = Path.of("src", "test", "resources", "input.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // When: running the main method with the input file
        try {
            int exitCode = App.navigate(new String[] { inputFile.toAbsolutePath().toString() });
            assertThat(exitCode).isZero();
        } finally {
            System.setOut(originalSystemOut);
        }

        // Then: output displayed should match the final position of the rovers
        String expectedOutput = """
                1 3 N
                5 1 E
                """;
        assertThat(outputStream.toString()).isEqualTo(expectedOutput);
    }

    @Test
    void navigate_whenValidInputWithMoreCases_shouldProduceExpectedOutput() throws Exception {
        // Given: a valid input file with multiple rover cases
        Path inputFile = Path.of("src", "test", "resources", "valid_input_more_cases.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // When: running the main method with the input file
        try {
            int exitCode = App.navigate(new String[] { inputFile.toAbsolutePath().toString() });
            assertThat(exitCode).isZero();
        } finally {
            System.setOut(originalSystemOut);
        }

        // Then: output displayed should match the final position of the rovers
        String expectedOutput = """
                0 0 N
                5 5 E
                0 0 S
                0 3 S
                0 10 W
                """;
        assertThat(outputStream.toString()).isEqualTo(expectedOutput);
    }

    @Test
    void navigate_whenNoInputFileProvided_shouldExitWithError() {
        // Given: no input file argument
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method without arguments
        try {
            int exitCode = App.navigate(new String[] {});
            assertThat(exitCode).isOne();
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Usage: java -jar rover.jar <input-file>\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }

    @Test
    void navigate_whenInputFileDoesNotExist_shouldExitWithError() {
        // Given: a non-existent input file
        String nonExistentFile = "non_existent_file.txt";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method with the non-existent file
        try {
            int exitCode = App.navigate(new String[] { nonExistentFile });
            assertThat(exitCode).isOne();
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Input file does not exist: " + nonExistentFile + "\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }

    @Test
    void navigate_whenInvalidRoverPositionFormat_shouldExitWithError() {
        // Given: an input file with invalid rover position format
        Path invalidInputFile = Path.of("src", "test", "resources", "invalid_position_input.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method with the invalid input file
        try {
            int exitCode = App.navigate(new String[] { invalidInputFile.toAbsolutePath().toString() });
            assertThat(exitCode).isOne();
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Invalid rover initial position format\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }

    @Test
    void navigate_whenInvalidRoverInstructionsFormat_shouldExitWithError() {
        // Given: an input file with invalid rover instructions format
        Path invalidInputFile = Path.of("src", "test", "resources", "invalid_instruction_input.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method with the invalid input file
        try {
            int exitCode = App.navigate(new String[] { invalidInputFile.toAbsolutePath().toString() });
            assertThat(exitCode).isOne();
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Invalid format of rover instructions\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }

    @Test
    void navigate_whenMissingRoverInstructions_shouldExitWithError() {
        // Given: an input file with missing rover instructions
        Path invalidInputFile = Path.of("src", "test", "resources", "missing_instruction_input.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method with the invalid input file
        try {
            int exitCode = App.navigate(new String[] { invalidInputFile.toAbsolutePath().toString() });
            assertThat(exitCode).isOne();
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Invalid input format\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }
}
