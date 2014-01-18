package com.github.javadojo;

/**
 * @author : fabrice
 */
public enum Direction {
    NORTH(90), EAST(0), SOUTH(270), WEST(180);

    private int degrees;

    private Direction(int degrees) {
        this.degrees = degrees;
    }

    public int getDegrees() {
        return degrees;
    }

    public static Direction getDirectionFromDegrees(int degrees) {
        for (Direction direction : Direction.values()) {
            if (direction.getDegrees()==degrees) {
                return direction;
            }
        }
        throw new RuntimeException(String.format("Cannot map orientation to degree '%s'", degrees));
    }
}
