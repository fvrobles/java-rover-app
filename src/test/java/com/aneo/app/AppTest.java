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

}
