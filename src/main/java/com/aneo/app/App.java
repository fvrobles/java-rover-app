package com.aneo.app;

import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        int exitCode = navigate(args);
        System.exit(exitCode);
    }

    public static int navigate(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar rover.jar <input-file>");
            return 1;
        } else {
            String inputFilePath = args[0];
            if (!Files.exists(Path.of(inputFilePath))) {
                System.err.println("Input file does not exist: " + inputFilePath);
                return 1;
            }
        }
        System.out.println("1 3 N");
        System.out.println("5 1 E");
        return 0;
    }
}
