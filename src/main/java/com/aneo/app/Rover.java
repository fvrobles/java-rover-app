package com.aneo.app;

public class Rover {
    private int x;
    private int y;
    private char direction;
    private Plateau plateau;

    public Rover(int x, int y, char direction, Plateau plateau) {
        if (x < 0 || y < 0 || x > plateau.maxX() || y > plateau.maxY()) {
            throw new IllegalArgumentException("Rover position out of bounds");
        }
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plateau = plateau;
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }

    public void followInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'L' -> direction = turnLeft(direction);
                case 'R' -> direction = turnRight(direction);
                case 'M' -> {
                    moveForward();
                }
                default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }

    private char turnLeft(char currentDirection) {
        return switch (currentDirection) {
            case 'N' -> 'W';
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            default -> throw new IllegalArgumentException("Invalid direction: " + currentDirection);
        };
    }

    private char turnRight(char currentDirection) {
        return switch (currentDirection) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> throw new IllegalArgumentException("Invalid direction: " + currentDirection);
        };
    }

    private void moveForward() {
        switch (direction) {
            case 'N' -> {
                if (y < plateau.maxY()) {
                    y++;
                }
            }
            case 'E' -> {
                if (x < plateau.maxX()) {
                    x++;
                }
            }
            case 'S' -> {
                if (y > 0) {
                    y--;
                }
            }
            case 'W' -> {
                if (x > 0) {
                    x--;
                }
            }
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

}
