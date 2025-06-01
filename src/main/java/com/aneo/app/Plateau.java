package com.aneo.app;

public record Plateau(int maxX, int maxY) {
    public Plateau {
        if (maxX < 1 || maxY < 1) {
            throw new IllegalArgumentException("Plateau dimensions must be positive");
        }
    }
}
