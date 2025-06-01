package com.aneo.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    void main_whenInputFileIsValid_shouldProduceExpectedOutput() throws Exception {
        // Given: a valid input file
        Path inputFile = Path.of("src", "test", "resources", "input.txt");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // When: running the main method with the input file
        try {
            App.main(new String[] { inputFile.toAbsolutePath().toString() });
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
    void main_whenNoInputFileProvided_shouldExitWithError() {
        // Given: no input file argument
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method without arguments
        try {
            App.main(new String[] {});
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Usage: java -jar rover.jar <input-file>\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }

    @Test
    void main_whenInputFileDoesNotExist_shouldExitWithError() {
        // Given: a non-existent input file
        String nonExistentFile = "non_existent_file.txt";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemErr = System.err;
        System.setErr(new PrintStream(outputStream));

        // When: running the main method with the non-existent file
        try {
            App.main(new String[] { nonExistentFile });
        } finally {
            System.setErr(originalSystemErr);
        }

        // Then: an error message should be printed to stderr
        String expectedErrorMessage = "Input file does not exist: " + nonExistentFile + "\n";
        assertThat(outputStream.toString()).isEqualTo(expectedErrorMessage);
    }

}
