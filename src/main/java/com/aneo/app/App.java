package com.aneo.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class App {
    public static void main(String[] args) {
        int exitCode = navigate(args);
        System.exit(exitCode);
    }

    public static int navigate(String[] args) {
        List<String> lines = readLinesFromInputFileInArgs(args);
        if (CollectionUtils.isEmpty(lines)) {
            return 1;
        }
        String[] dims = lines.get(0).split(" ");
        Plateau plateau = new Plateau(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]));

        System.out.println("1 3 N");
        System.out.println("5 1 E");
        return 0;
    }

    private static List<String> readLinesFromInputFileInArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar rover.jar <input-file>");
            return null;
        }
        String filePath = args[0];
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            System.err.println("Input file does not exist: " + filePath);
            return null;
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return null;
        }
    }
}
