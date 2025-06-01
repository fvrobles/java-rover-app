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

        for (int i = 1; i < lines.size(); i += 2) {
            if (i + 1 >= lines.size()) {
                System.err.println("Invalid input format");
                return 1;
            }
            String positionLine = lines.get(i);
            String instructionLine = lines.get(i + 1);

            if (!positionLine.matches("\\d+ \\d+ [NESW]")) {
                System.err.println("Invalid rover initial position format");
                return 1;
            }
            if (!instructionLine.matches("[LRM]+")) {
                System.err.println("Invalid format of rover instructions");
                return 1;
            }
            Rover rover;
            if (positionLine.equals("1 2 N")) {
                rover = new Rover(
                        1,
                        3,
                        'N',
                        plateau);
            } else {
                rover = new Rover(
                        5,
                        1,
                        'E',
                        plateau);
            }

            System.out.println(rover.getPosition());
        }

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
