package com.aneo.app;

public class Rover {
    private int x;
    private int y;
    private char direction;
    private Plateau plateau;

    public Rover(int x, int y, char direction, Plateau plateau) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plateau = plateau;
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }

}
